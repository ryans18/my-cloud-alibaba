package com.ryans.controller;

import com.ryans.fegin.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Author：Ryans
 * Date：Created in 2023/8/23 21:58
 * Introduction：
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;
    private static final String REST_URL = "http://provider-user";

    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/say")
    public String say(String name) {
        String url = REST_URL + "/hello/say?name=" + name;
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/say2")
    public String say2(String name) {
        return userFeignService.say(name);
    }
}