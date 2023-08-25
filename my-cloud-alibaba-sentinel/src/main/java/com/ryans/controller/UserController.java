package com.ryans.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ryans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : Ryans
 * Date : 2023/8/25
 * Introduction :
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/detail")
    public String detail() {
        return userService.queryUser();
    }

    @GetMapping("/save")
    public String save() {
        userService.queryUser();
        return "保存成功";
    }
}
