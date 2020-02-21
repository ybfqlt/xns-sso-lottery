package com.ns.redis.service.impl;

import com.ns.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @Author: xns
 * @Date: 20-1-31 下午5:19
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void put(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key,value,seconds,TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 如果key存在的话返回false,不存在的话返回true
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    @Override
    public Boolean setNx(String key, String value, Long timeout) {
        Boolean setIfAbsent = redisTemplate.opsForValue().setIfAbsent(key,value);
        if(timeout != null){
            redisTemplate.expire(key,timeout,TimeUnit.SECONDS);
        }
        return setIfAbsent;
    }

    @Override
    public void putList(String key, List<String> list) {
        Long aLong = redisTemplate.opsForList().rightPushAll(key, list);
    }

    @Override
    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

}
