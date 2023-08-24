package com.ryans.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : Ryans
 * Date : 2023/8/24
 * Introduction :
 */
@RestController
@RefreshScope  // 动态刷新
public class IndexController {

    // 不能用user，user会去拿系统变量，这里是bxc2019003
   /* @Value("${my.name}")
    private String name;
    @Value("${my.age}")
    private String age;
    @GetMapping("/getUser")
    public String getUser() {
        return String.format("{name: %s, age: %s}", name, age);
    }*/

    @Value("${source}")
    private String source;
    @GetMapping("/source")
    public String source() {
        return source;
    }
}
