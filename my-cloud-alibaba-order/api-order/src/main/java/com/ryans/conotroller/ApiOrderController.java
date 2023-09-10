package com.ryans.conotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Author：Ryans
 * Date：Created in 2023/9/10 21:34
 * Introduction：
 */
@RestController
public class ApiOrderController {

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/do/{orderId}")
    public String orderMysql(@PathVariable("orderId") int orderId, int userId) {
        String url = "http://service-order" + "/seckill/do/" + orderId + "?userId="+ userId;
        restTemplate.getForObject(url, String.class);
        return "成功";
    }
}