package com.ryans.controller;

import com.ryans.entity.TbOrder;
import com.ryans.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author：Ryans
 * Date：Created in 2023/8/26 22:57
 * Introduction：
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/add")
    public int add(@RequestBody TbOrder tbOrder) {
        log.info("开始新增订单");
        int add = orderService.add(tbOrder);
        log.info("新增订单完毕：" + add);
        return add;
    }
}