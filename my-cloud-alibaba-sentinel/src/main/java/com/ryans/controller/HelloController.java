package com.ryans.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Author : Ryans
 * Date : 2023/8/25
 * Introduction :
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

//    @SentinelResource(value = "hello", blockHandler = "helloException")
    @GetMapping("/")
    public String hello() throws InterruptedException {
        return "hello, ryans ";
    }

    @GetMapping("/detail")
    public String detail() {
        return "ryans";
    }

    public String helloException(BlockException blockException)  {
        return "请求过于频繁，请稍后重试";
    }
}
