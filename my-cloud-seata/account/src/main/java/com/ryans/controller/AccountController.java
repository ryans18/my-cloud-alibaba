package com.ryans.controller;

import com.ryans.entity.Account;
import com.ryans.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author：Ryans
 * Date：Created in 2023/8/26 23:02
 * Introduction：
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/update")
    public Integer update(@RequestBody Account account) {
        log.info("开始更新账户");
        int update = accountService.update(account);
        log.info("账户更新完毕：" + update);
        return update;
    }
}