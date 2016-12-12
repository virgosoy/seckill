package com.soy.seckill.exception;

/**
 * 重复秒杀异常
 * Created by Soy on 2016/12/12.
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
