package com.soy.seckill.service.impl;

import com.soy.seckill.dao.SeckillDao;
import com.soy.seckill.dao.SuccessKilledDao;
import com.soy.seckill.dao.cache.RedisDao;
import com.soy.seckill.dto.Exposer;
import com.soy.seckill.dto.SeckillExecution;
import com.soy.seckill.entity.Seckill;
import com.soy.seckill.entity.SuccessKilled;
import com.soy.seckill.enums.StateEnum;
import com.soy.seckill.exception.RepeatKillException;
import com.soy.seckill.exception.SeckillCloseException;
import com.soy.seckill.exception.SeckillException;
import com.soy.seckill.service.SeckillService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Soy on 2016/12/12.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    @Autowired
    private RedisDao redisDao;

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,10);
    }

    @Override
    public Seckill getById(int seckillId) {
        return seckillDao.queryById(seckillId);
    }

//    @Override
//    public Exposer exposerSeckillUrl(int seckillId) {
//        Seckill seckill = seckillDao.queryById(seckillId);
//        if (seckill == null){
//            return new Exposer(false,seckillId);
//        }
//        Date startTime = seckill.getStartTime();
//        Date endTime = seckill.getEndTime();
//        //系统当前时间
//        Date now = new Date();
//        //如果在时间范围内
//        if (now.after(startTime) && now.before(endTime)){
//            String md5 = getMd5(seckillId);
//            //暴露秒杀地址
//            return new Exposer(true, seckillId, md5);
//        }else{
//            return new Exposer(false,now,startTime,endTime);
//        }
//    }

    @Override
    //优化：使用redis缓存，在超时的基础上维护一致性。
    //由于库存做处理，故在此不需要保证库存的一致性。
    public Exposer exposerSeckillUrl(int seckillId) {
        //从缓存中获取
        Seckill seckill = redisDao.getSeckill(seckillId);
        //如果没获取到。
        if(seckill==null){
            seckill = seckillDao.queryById(seckillId);
            if(seckill==null){ //如果从数据库获取的为空
                return new Exposer(false,seckillId);
            }
            //放入redis缓存中
            String msg = redisDao.putSeckill(seckill);
            if(!"OK".equalsIgnoreCase(msg)){
                //如果保存到redis不成功
                logger.warn("seckillId={}，保存到redis失败！！！",seckillId);
            }
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //系统当前时间
        Date now = new Date();
        //如果在时间范围内
        if (now.after(startTime) && now.before(endTime)){
            String md5 = getMd5(seckillId);
            //暴露秒杀地址
            return new Exposer(true, seckillId, md5);
        }else{
            return new Exposer(false,now,startTime,endTime);
        }
    }

    @Override
    //优化：使用存储过程
    public SeckillExecution executeSeckill(int seckillId, String userPhone, String md5)
            throws RepeatKillException, SeckillCloseException, SeckillException {
        if(md5==null || !md5.equals(getMd5(seckillId))){
            return new SeckillExecution(seckillId,StateEnum.DATA_REWRITE);
        }
        Date killTime = new Date();
        Map<String,Object> map = new HashMap<>();
        map.put("seckillId",seckillId);
        map.put("killTime",killTime);
        map.put("userPhone",userPhone);
        map.put("result",null);

        try {
            //执行存储过程
            seckillDao.killByProcedure(map);
            //获取result
            int result = MapUtils.getIntValue(map,"result",-2);
            if(result == 1){
                SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                return new SeckillExecution(seckillId,StateEnum.SUCCESS,successKilled);
            }else {
                return new SeckillExecution(seckillId,StateEnum.stateOf(result));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new SeckillExecution(seckillId,StateEnum.INNER_ERROR);
        }
    }

//    @Override
//    @Transactional
//    public SeckillExecution executeSeckill(int seckillId, String userPhone, String md5)
//            throws RepeatKillException, SeckillCloseException, SeckillException {
//        if(md5==null || md5.equals(getMd5(seckillId))){
//            return new SeckillExecution(seckillId,StateEnum.DATA_REWRITE);
//        }
//        try {
//            int count = successKilledDao.insertSuccessKilled(seckillId, userPhone);
//            //插入成功，表示之前未秒杀
//            if(count>0){
//                Date now = new Date();
//                int number = seckillDao.reduceNumber(seckillId, now);
//                //如果减库存成功
//                if (number>0){
//                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
//                    return new SeckillExecution(seckillId, StateEnum.SUCCESS,successKilled);
//                }else{
//                    throw new SeckillCloseException("秒杀结束！");
//                }
//            }else{
//                throw new RepeatKillException("重复秒杀！");
//            }
//        } catch (SeckillCloseException | RepeatKillException e) {
//            throw e;
//        } catch (Exception e){
//            logger.error(e.getMessage(),e);
//            //所有检查型异常都转化为运行期异常
//            throw new SeckillException(e.getMessage(),e);
//        }
//    }

    //MD5的盐
    private final String salt = "fwebdlbwea@!#$#%gW$H$%Bw5423f3rh45$%w45h";

    private String getMd5(int seckillId){
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;

    }
}
