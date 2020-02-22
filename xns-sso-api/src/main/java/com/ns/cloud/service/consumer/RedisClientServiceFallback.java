package com.ns.cloud.service.consumer;

import com.ns.cloud.dto.Result;
import com.ns.cloud.utils.JsonUtils;
import feign.hystrix.FallbackFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: xns
 * @Date: 20-2-1 下午10:02
 */
@Component
public class RedisClientServiceFallback implements FallbackFactory<RedisClientService> {
    @Override
    public RedisClientService create(Throwable throwable) {
        return new RedisClientService() {
            @Override
            public String put(String key, String value, long seconds) {
               return null;
            }

            @Override
            public String get(String key) {
                return null;
            }

            @Override
            public String delete(String key) {
                return null;
            }

            @Override
            public Boolean setnx(String key, String value, Long timeout) {
                return null;
            }

            @Override
            public Boolean setList(String key, List<String> list) {
                return null;
            }

            @Override
            public String getListOfOne(String key) {
                return null;
            }
        };
    }
}
