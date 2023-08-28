package com.ryans.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : Ryans
 * Date : 2023/8/28
 * Introduction :
 */
@RestController
public class IndexController {
    @GetMapping("/index")
    public String index(@RequestHeader("name") String name) {
        return "user: " + name;
    }
    @GetMapping("/status")
    public Map<String, Object> status() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", "");
        return result;
    }
}
