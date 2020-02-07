package com.ns.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: xns
 * @Date: 20-1-30 下午12:52
 */
@EnableEurekaClient //服务提供者
@EnableDiscoveryClient //服务消费者
@EnableFeignClients(basePackages = {"com.ns.cloud"})
@SpringBootApplication
@MapperScan(basePackages = "com.ns.cloud.mapper")
public class ServiceSSOAPP {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSSOAPP.class,args);
    }
}
