package com.loongzee.exception;

/**
 * @program: flash-sale
 * @description: 秒杀关闭异常
 * @author: Loongzee
 * @create: 2019-04-04 16:39
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
