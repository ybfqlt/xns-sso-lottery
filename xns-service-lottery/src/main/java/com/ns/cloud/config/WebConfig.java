package com.ns.cloud.config;

import com.ns.cloud.Interceptor.WebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: xns
 * @Date: 20-2-6 下午6:36
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Bean
    public RestTemplate getRestemplate(){
        return new RestTemplate();
    }

    //必须加，否则WebInterceptor会为null,不能注册到容器
    @Bean
    public WebInterceptor webInterceptor(){
        return new WebInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webInterceptor()).addPathPatterns("/**").excludePathPatterns("/static");
    }
}
