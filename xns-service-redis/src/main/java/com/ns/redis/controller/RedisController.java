package com.ns.redis.controller;

import com.ns.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xns
 * @Date: 20-1-31 下午5:33
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @PostMapping("/put")
    public String put(String key, String value, long seconds) {
        redisService.put(key, value, seconds);
        return "yes";
    }

    @GetMapping("/get")
    public String get(String key) {
        Object o = redisService.get(key);
        if (o != null) {
            String str = String.valueOf(o);
            return str;
        }
        //方便后面使用
        return null;
    }

    @PostMapping("/delete")
    public String delete(String key) {
        Boolean a = redisService.delete(key);
        return "yes";
    }

    @PostMapping("/setnx")
    public Boolean setnx(String key, String value, Long timeout){
        return redisService.setNx(key,value,timeout);
    }
}
