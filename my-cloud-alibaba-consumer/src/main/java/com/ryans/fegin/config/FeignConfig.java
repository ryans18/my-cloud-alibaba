package com.ryans.fegin.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author：Ryans
 * Date：Created in 2023/8/27 22:29
 * Introduction：
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;//Full是详细日志
    }
}