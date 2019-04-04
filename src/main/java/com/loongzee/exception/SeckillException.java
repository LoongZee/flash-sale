package com.loongzee.exception;

/**
 * @program: flash-sale
 * @description: 所有业务异常
 * @author: Loongzee
 * @create: 2019-04-04 16:38
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
