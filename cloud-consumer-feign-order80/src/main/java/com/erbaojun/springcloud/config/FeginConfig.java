package com.erbaojun.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author outakaramoto
 * @create 2020/5/31 12:43 上午
 */
@Configuration
public class FeginConfig {
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
