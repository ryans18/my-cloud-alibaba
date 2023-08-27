package com.ryans.controller;

import com.ryans.entity.Account;
import com.ryans.entity.TbOrder;
import com.ryans.entity.Storage;
import com.ryans.result.Result;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Author：Ryans
 * Date：Created in 2023/8/26 23:05
 * Introduction：
 */
@RestController
@Slf4j
public class ShopController {

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/buy")
    @GlobalTransactional(name = "spring-cloud-demo-tx")
    public Result consume(@RequestParam("userId")int userId,
                        @RequestParam("code")String code, // 商品code
                        @RequestParam("price") int price, // 单价
                        @RequestParam("num")int num) {   // 消费数量
        log.info("开始下单");
        int money = price * num;  // 商品的总价 = 单价 * 数量
        Account account = new Account(null, userId, money);
        Integer accountResult = restTemplate.postForObject("http://seata-provider-account/account/update", account, Integer.class);
        Storage storage = new Storage(null, code, num);
        Integer storageResult = restTemplate.postForObject("http://seata-provider-storage/storage/update", storage, Integer.class);
        TbOrder tbOrder = new TbOrder(null, 100, "phone", num, money);
        Integer orderResult = restTemplate.postForObject("http://seata-provider-order/order/add", tbOrder, Integer.class);
        log.info("下单执行完毕");
        if (orderResult != null && orderResult > 0 && accountResult != null && accountResult > 0 && storageResult != null && storageResult > 0) {
            return Result.success("下单成功");
        }
        return Result.fail("下单失败");
    }
}