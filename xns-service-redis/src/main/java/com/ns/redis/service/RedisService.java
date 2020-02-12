package com.ns.redis.service;

/**
 * @Author: xns
 * @Date: 20-1-31 下午4:29
 */
public interface RedisService {

    /**
     *
     * @param key
     * @param value
     * @param seconds
     */
    public void put(String key,Object value,long seconds);

    public Object get(String key);

    public Boolean delete(String key);
}
