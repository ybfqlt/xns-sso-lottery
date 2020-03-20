package com.ns.redis.config;


import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
@EnableCaching
public class RedisConfig {

    //配置RedisTemplate
    @Bean
    public RedisTemplate redisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        lettuceConnectionFactory.setValidateConnection(false);
        lettuceConnectionFactory.setShareNativeConnection(false);
        RedisTemplate redisTemplate = new RedisTemplate();
        //注入Lettuce连接工厂
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }
        
}