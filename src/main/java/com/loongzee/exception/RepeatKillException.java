package com.loongzee.exception;

/**
 * @program: flash-sale
 * @description: 重复秒杀异常
 * @author: Loongzee
 * @create: 2019-04-04 16:37
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
