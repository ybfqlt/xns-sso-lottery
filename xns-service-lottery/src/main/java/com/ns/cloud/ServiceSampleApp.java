package com.ns.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: xns
 * @Date: 20-2-6 下午6:46
 */
@EnableEurekaClient //服务提供者
@EnableDiscoveryClient //服务消费者
@EnableAsync
@EnableCircuitBreaker //对hystrix熔断机制的支持
@EnableFeignClients(basePackages = {"com.ns.cloud"})
@SpringBootApplication(scanBasePackages = "com.ns.cloud")
public class ServiceSampleApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSampleApp.class,args);
    }
}
