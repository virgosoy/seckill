package com.soy.seckill.dao;

import com.soy.seckill.entity.Seckill;
import com.soy.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Soy on 2016/12/12.
 */
public interface SuccessKilledDao {

    /**
     * 插入秒杀明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") String userPhone);

    /**
     * 根据id查询秒杀明细及秒杀库存
     * @param seckillId
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWitchSeckill(@Param("seckillId") long seckillId, @Param("userPhone") String userPhone);

}
