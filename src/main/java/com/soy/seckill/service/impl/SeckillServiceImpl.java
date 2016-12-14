package com.soy.seckill.service.impl;

import com.soy.seckill.dao.SeckillDao;
import com.soy.seckill.dao.SuccessKilledDao;
import com.soy.seckill.dto.Exposer;
import com.soy.seckill.dto.SeckillExecution;
import com.soy.seckill.entity.Seckill;
import com.soy.seckill.enums.StateEnum;
import com.soy.seckill.exception.RepeatKillException;
import com.soy.seckill.exception.SeckillCloseException;
import com.soy.seckill.exception.SeckillException;
import com.soy.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

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

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,10);
    }

    @Override
    public Seckill getById(int seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exposerSeckillUrl(int seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null){
            return new Exposer(false,seckillId);
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
    @Transactional
    public SeckillExecution executeSeckill(int seckillId, String userPhone, String md5)
            throws RepeatKillException, SeckillCloseException, SeckillException {

        try {
            int count = successKilledDao.insertSuccessKilled(seckillId, userPhone);
            //插入成功，表示之前未秒杀
            if(count>0){
                Date now = new Date();
                int number = seckillDao.reduceNumber(seckillId, now);
                //如果减库存成功
                if (number>0){
                    return new SeckillExecution(seckillId, StateEnum.SUCCESS);
                }else{
                    throw new SeckillCloseException("秒杀结束！");
                }
            }else{
                throw new RepeatKillException("重复秒杀！");
            }
        } catch (SeckillCloseException | RepeatKillException e) {
            throw e;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            //所有检查型异常都转化为运行期异常
            throw new SeckillException(e.getMessage(),e);
        }
    }

    //MD5的盐
    private final String salt = "fwebdlbwea@!#$#%gW$H$%Bw5423f3rh45$%w45h";

    private String getMd5(int seckillId){
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;

    }
}
