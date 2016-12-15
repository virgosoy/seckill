package com.soy.seckill.service.impl;

import com.soy.seckill.dto.Exposer;
import com.soy.seckill.dto.SeckillExecution;
import com.soy.seckill.entity.Seckill;
import com.soy.seckill.exception.RepeatKillException;
import com.soy.seckill.exception.SeckillCloseException;
import com.soy.seckill.exception.SeckillException;
import com.soy.seckill.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Soy on 2016/12/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("list={}",seckillList);
    }

    @Test
    public void getById() throws Exception {
        Seckill seckill = seckillService.getById(1002);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void exposerSeckillUrl() throws Exception {
        Exposer exposer = seckillService.exposerSeckillUrl(1002);
        logger.info("exposer={}",exposer);
    }

    @Test
    public void executeSeckill() throws Exception {
        try {
            SeckillExecution seckillExecution =
                seckillService.executeSeckill(1002, "11121119121", "baf3d2c0cc0c30ada614d258e9d20f93");
            logger.info("result={}",seckillExecution);
        } catch (RepeatKillException e){
            logger.error(e.getMessage(),e);
        } catch (SeckillCloseException e){
            logger.error(e.getMessage(),e);
        }
    }

    @Test
    public void testSeckillLogic(){
        int id = 1003;
        String userPhone = "12343459039";
        Exposer exposer = seckillService.exposerSeckillUrl(id);
        //如果暴露了url开始秒杀
        if(exposer.isExposed()){
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution =
                        seckillService.executeSeckill(id, userPhone, md5);
                logger.info("result={}", seckillExecution);
            } catch (RepeatKillException | SeckillCloseException e){
                logger.error(e.getMessage(),e);
            }
        }else {
            //秒杀未开启
            logger.warn("exposer={}",exposer);
        }


    }
}