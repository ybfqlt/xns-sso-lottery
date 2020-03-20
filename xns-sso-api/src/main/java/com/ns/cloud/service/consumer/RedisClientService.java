package com.ns.cloud.service.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: xns
 * @Date: 20-2-1 下午10:00
 */
@FeignClient(value = "SERVICECLOUD-REDIS",fallbackFactory = RedisClientServiceFallback.class)
public interface RedisClientService {

    @PostMapping("/redis/put")
    public String put(@RequestParam String key,@RequestParam String value,@RequestParam long seconds);

    @GetMapping("/redis/get")
    public String get(@RequestParam String key);

    @PostMapping("/redis/delete")
    public String delete(String key);

    @PostMapping("/redis/setnx")
    public Boolean setnx(@RequestParam String key, @RequestParam String value, @RequestParam Long timeout);

    @PostMapping("/redis/setlist")
    public Boolean setList(@RequestParam("key") String key, @RequestParam(value="list",required = true) List<String> list);

    @PostMapping("/redis/getone")
    public String getListOfOne(@RequestParam("key") String key);

    @GetMapping
    public RedisTemplate getRedisTemplate();
}
