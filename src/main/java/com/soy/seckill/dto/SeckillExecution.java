package com.soy.seckill.dto;

import com.soy.seckill.entity.SuccessKilled;
import com.soy.seckill.enums.StateEnum;

/**
 * Created by Soy on 2016/12/12.
 */
public class SeckillExecution {
    //id
    private int seckillId;
    //秒杀执行结果状态
    private StateEnum state;
    //秒杀成功对象
    private SuccessKilled successKilled;

    /**
     * 秒杀成功时使用的构造函数
     * @param seckillId
     * @param state
     * @param successKilled
     */
    public SeckillExecution(int seckillId, StateEnum state, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = state;
        this.successKilled = successKilled;
    }

    /**
     * 秒杀失败时使用的构造函数
     * @param seckillId
     * @param state
     */
    public SeckillExecution(int seckillId, StateEnum state) {
        this.seckillId = seckillId;
        this.state = state;
    }

    public int getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(int seckillId) {
        this.seckillId = seckillId;
    }



    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", successKilled=" + successKilled +
                '}';
    }
}
