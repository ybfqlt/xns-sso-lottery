package com.ns.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: xns
 * @Date: 20-2-23 上午10:16
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulStartApp {
    public static void main(String[] args) {
        SpringApplication.run(ZuulStartApp.class,args);
    }
}
