package com.soy.seckill.exception;

/**
 * 秒杀相关业务异常
 * Created by Soy on 2016/12/12.
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
