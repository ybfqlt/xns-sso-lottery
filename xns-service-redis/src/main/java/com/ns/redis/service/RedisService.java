package com.ns.redis.service;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @Author: xns
 * @Date: 20-1-31 下午4:29
 */
public interface RedisService {

    /**
     *存入字符串
     * @param key
     * @param value
     * @param seconds
     */
    public void put(String key,Object value,long seconds);

    public Object get(String key);

    public Boolean delete(String key);

    public Boolean setNx(String key,String value,Long timeout);

    /**
     * 存入List
     * @param key
     * @param list
     * @return
     */
    public void putList(String key, List<String> list);


    public String getOneOfList(String key);
}
