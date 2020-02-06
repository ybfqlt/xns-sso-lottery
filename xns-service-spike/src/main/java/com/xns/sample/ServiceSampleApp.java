package com.xns.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: xns
 * @Date: 20-2-6 下午6:46
 */
@EnableEurekaClient //服务提供者
@SpringBootApplication
public class ServiceSampleApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSampleApp.class,args);
    }
}
