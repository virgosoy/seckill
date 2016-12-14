package com.soy.seckill.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.omg.CORBA.UNKNOWN;

/**
 * 执行秒杀后状态（数据字典）
 * Created by Soy on 2016/12/12.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StateEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");

    private int state;
    private String stateInfo;

    StateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static StateEnum stateOf(int state){
        for(StateEnum stateEnum : values()){
            if(stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
