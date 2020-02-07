package com.ns.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: xns
 * @Date: 20-1-31 下午6:14
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka7001App {
    public static void main(String[] args) {
        SpringApplication.run(Eureka7001App.class, args);
    }
}
