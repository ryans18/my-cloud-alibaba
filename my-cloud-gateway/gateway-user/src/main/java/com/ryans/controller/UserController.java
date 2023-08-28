package com.ryans.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : Ryans
 * Date : 2023/8/28
 * Introduction :
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/get")
    public String get() {
        return "user:ryans";
    }
}
