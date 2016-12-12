package com.soy.seckill.dao;

import com.soy.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Soy on 2016/12/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        int i = successKilledDao.insertSuccessKilled(1002, "13412341234");
        System.out.println(i);
    }

    @Test
    public void queryByIdWitchSeckill() throws Exception {
        SuccessKilled successKilled = successKilledDao.queryByIdWitchSeckill(1002, "13412341234");
        System.out.println(successKilled);
    }

}