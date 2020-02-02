package com.ns.sso.service.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: xns
 * @Date: 20-2-1 下午10:00
 */
@FeignClient(value = "SERVICECLOUD-REDIS",fallbackFactory = RedisClientServiceFallback.class)
public interface RedisClientService {

    @PostMapping("/redis/put")
    public String put(String key,String value,long seconds);

    @GetMapping("/redis/get")
    public String get(String key);
}
