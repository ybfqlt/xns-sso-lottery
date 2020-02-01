package com.ns.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: xns
 * @Date: 20-1-30 下午9:54
 */
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
public class RedisApp {
    public static void main(String[] args) {
        SpringApplication.run(RedisApp.class,args);
    }
}
