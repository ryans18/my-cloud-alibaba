package com.ryans;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author : Ryans
 * Date : 2023/8/24
 * Introduction : 可以注册多个配置文件, 动态刷新
 */
@SpringBootApplication
@NacosPropertySource(dataId = "dataId1", autoRefreshed = true)
@NacosPropertySource(dataId = "dataId2", autoRefreshed = true)
public class NacosConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
    }
}
