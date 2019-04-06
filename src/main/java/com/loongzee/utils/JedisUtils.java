package com.loongzee.utils;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @program: flash-sale
 * @description:
 * @author: Loongzee
 * @create: 2019-04-06 14:12
 */
public class JedisUtils {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";   //若key不存在就进行set操作，若ket存在就不进行操作
    private static final String SET_WITH_EXPIRE_TIME = "PX";   //给key加过期时间，具体时间由第五个参数定
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取分布式锁
     *
     * @param jedis      Redis客户端
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间, 单位毫秒
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        return LOCK_SUCCESS.equals(result);
    }

    /**
     * 释放分布式锁
     *
     * @param jedis     Redis客户端
     * @param lockKey   锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {


        //采用lua脚本是为了保证原子性
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        return result.equals(RELEASE_SUCCESS);

    }
}