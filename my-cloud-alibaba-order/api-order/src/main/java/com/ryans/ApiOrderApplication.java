package com.ryans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Author：Ryans
 * Date：Created in 2023/9/10 16:34
 * Introduction：
 */
@SpringBootApplication
public class ApiOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiOrderApplication.class, args);
    }
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}