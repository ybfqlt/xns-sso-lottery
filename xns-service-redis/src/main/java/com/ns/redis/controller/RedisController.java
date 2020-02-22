package com.ns.redis.controller;

import com.ns.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 不加@RequestParam会报错
     * @param key
     * @param list
     * @return
     */
    @PostMapping("/setlist")
    public Boolean setList(@RequestParam("key") String key, @RequestParam(value="list",required = true) List<String> list){
        redisService.putList(key,list);
        return true;
    }

    @PostMapping("/getone")
    public String getListOfOne(@RequestParam("key") String key){
        String oneOfList = redisService.getOneOfList(key);
        return oneOfList;
    }
}
