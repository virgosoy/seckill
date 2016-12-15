package com.soy.seckill.dao.cache;

import com.soy.seckill.dao.SeckillDao;
import com.soy.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Soy on 2016/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class RedisDaoTest {
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void putSeckill() throws Exception {
        int seckillId = 1002;
        Seckill seckill = seckillDao.queryById(seckillId);
        if(seckill!=null) {
            String msg = redisDao.putSeckill(seckill);
            System.out.println(msg);
        }
    }

    @Test
    public void getSeckill() throws Exception {
        int seckillId = 1002;
        Seckill seckill = redisDao.getSeckill(seckillId);
        if(seckill!=null){
            System.out.println(seckill);
        }
    }

    @Test
    public void testRedis(){
        int seckillId = 1003;
        Seckill seckill = redisDao.getSeckill(seckillId);
        if(seckill==null){
            seckill = seckillDao.queryById(seckillId);
            if(seckill!=null){
                String msg = redisDao.putSeckill(seckill);
                System.out.println(msg);
                seckill = redisDao.getSeckill(seckillId);
                System.out.println(seckill);
            }
        }
    }
}