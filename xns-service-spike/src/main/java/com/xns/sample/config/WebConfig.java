package com.xns.sample.config;

import com.xns.sample.Interceptor.WebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: xns
 * @Date: 20-2-6 下午6:36
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

    //必须加，否则WebInterceptor会为null,不能注册到容器
    @Bean
    WebInterceptor webInterceptor(){
        return new WebInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webInterceptor()).addPathPatterns("/**").excludePathPatterns("/static");
    }
}
