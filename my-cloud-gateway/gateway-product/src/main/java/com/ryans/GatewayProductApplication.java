package com.ryans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author : Ryans
 * Date : 2023/8/28
 * Introduction :
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayProductApplication.class, args);
    }
}
