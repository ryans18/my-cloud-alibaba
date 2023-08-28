package com.ryans.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : Ryans
 * Date : 2023/8/28
 * Introduction :
 */
@RestController
public class IndexController {
    @GetMapping("/index")
    public String index() {
        return "product";
    }
}
