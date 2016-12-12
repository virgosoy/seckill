package com.soy.seckill.dao;

import com.soy.seckill.dao.SeckillDao;
import com.soy.seckill.entity.Seckill;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Soy on 2016/12/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() throws Exception {
        int i = seckillDao.reduceNumber(1002, new Date());
        System.out.println(i);
    }

    @Test
    public void queryById() throws Exception {
        Seckill seckill = seckillDao.queryById(1002);
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0, 2);
        for (Seckill seckill :seckills) {
            System.out.println(seckill);
        }
    }

}