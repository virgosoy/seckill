package com.soy.seckill.exception;

/**
 * 秒杀关闭异常
 * Created by Soy on 2016/12/12.
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
