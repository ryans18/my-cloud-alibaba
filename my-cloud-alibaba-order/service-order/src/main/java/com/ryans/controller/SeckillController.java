package com.ryans.controller;

import com.ryans.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author：Ryans
 * Date：Created in 2023/9/10 21:59
 * Introduction：
 */
@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
//    @Qualifier("seckillNoLockService")  // 无锁
//    @Qualifier("seckillJvmLockService")    // Jvm锁
    @Qualifier("seckillMysqlLockService")    // MySql分布式锁锁
    private SeckillService seckillService;

    @GetMapping("/do/{goodsId}")
    public String orderMysql(@PathVariable("goodsId") int goodsId, int userId) {
        System.out.println("goodsId: " + goodsId + "userId: " + userId);
        seckillService.grabOrder(goodsId, userId);
        return "";
    }
}