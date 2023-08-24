package com.ryans.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : Ryans
 * Date : 2023/8/24
 * Introduction :
 */
@RestController
public class ConfigController {

    @NacosValue(value = "${name}")
    private String name;
    @NacosValue(value = "${age:22}", autoRefreshed = true)
    private String age;
    @GetMapping("/getConfig")
    public String getConfig() {
        return name + " -- " + age;
    }
}
