package com.ryans.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author：Ryans
 * Date：Created in 2023/9/11 22:39
 * Introduction：
 */
@Configuration
public class RedisConfig {

    @Bean(value = "redissionRed1")
    public RedissonClient redisson1() {
        Config config = new Config();
        config.useSingleServer().setAddress("139.155.68.87:6379").setDatabase(0);
        return Redisson.create(config);
    }
    @Bean(value = "redissionRed2")
    public RedissonClient redisson2() {
        Config config = new Config();
        config.useSingleServer().setAddress("139.155.68.87:6380").setDatabase(0);
        return Redisson.create(config);
    }
    @Bean(value = "redissionRed3")
    public RedissonClient redisson3() {
        Config config = new Config();
        config.useSingleServer().setAddress("139.155.68.87:6381").setDatabase(0);
        return Redisson.create(config);
    }
    @Bean(value = "redissionRed4")
    public RedissonClient redisson4() {
        Config config = new Config();
        config.useSingleServer().setAddress("139.155.68.87:6382").setDatabase(0);
        return Redisson.create(config);
    }
    @Bean(value = "redissionRed5")
    public RedissonClient redisson5() {
        Config config = new Config();
        config.useSingleServer().setAddress("139.155.68.87:6383").setDatabase(0);
        return Redisson.create(config);
    }
}