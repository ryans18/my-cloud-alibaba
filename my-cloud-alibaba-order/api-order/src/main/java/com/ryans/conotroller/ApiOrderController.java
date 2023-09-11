package com.ryans.conotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Author：Ryans
 * Date：Created in 2023/9/10 21:34
 * Introduction：
 */
@RestController
@RequestMapping("/seckill")
public class ApiOrderController {

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/do/{goodsId}")
    public String orderMysql(@PathVariable("goodsId") int goodsId, int userId) {
        String url = "http://service-order" + "/seckill/do/" + goodsId + "?userId="+ userId;
        return restTemplate.getForObject(url, String.class);
    }
}