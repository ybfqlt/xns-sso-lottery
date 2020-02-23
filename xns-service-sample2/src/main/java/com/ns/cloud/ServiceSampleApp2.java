package com.ns.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: xns
 * @Date: 20-2-12 下午1:13
 */
@EnableEurekaClient //服务提供者
@EnableDiscoveryClient //服务消费者
@EnableFeignClients   //扫描和注册feign客户端的bean定义，如果不加的话，efign客户端无法用用@Autowired注入
@SpringBootApplication
public class ServiceSampleApp2 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSampleApp2.class,args);
    }
}
