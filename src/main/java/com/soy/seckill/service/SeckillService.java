package com.soy.seckill.service;

import com.soy.seckill.dto.Exposer;
import com.soy.seckill.dto.SeckillExecution;
import com.soy.seckill.entity.Seckill;
import com.soy.seckill.exception.RepeatKillException;
import com.soy.seckill.exception.SeckillCloseException;
import com.soy.seckill.exception.SeckillException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Soy on 2016/12/12.
 */
public interface SeckillService {
    /**
     * 查询所有秒杀商品
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 根据id获取秒杀商品
     * @param seckillId
     * @return
     */
    Seckill getById(int seckillId);


    /**
     * 获取秒杀地址。
     * 秒杀开启时输出秒杀接口地址，
     * 否则输出系统时间和秒杀时间。
     * @param seckillId
     * @return
     */
    Exposer exposerSeckillUrl(int seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(int seckillId, String userPhone, String md5)
            throws RepeatKillException, SeckillCloseException, SeckillException;


}
