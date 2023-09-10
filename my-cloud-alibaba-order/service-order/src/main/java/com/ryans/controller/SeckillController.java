package com.ryans.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author：Ryans
 * Date：Created in 2023/9/10 21:59
 * Introduction：
 */
@RestController
public class SeckillController {

    @GetMapping("/do/{goodsId}")
    public String orderMysql(@PathVariable("goodsId") int goodsId, int userId) {
        System.out.println("goodsId: " + goodsId + "userId: " + userId);
        return "";
    }
}