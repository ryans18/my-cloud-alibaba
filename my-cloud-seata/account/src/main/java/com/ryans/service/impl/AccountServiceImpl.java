package com.ryans.service.impl;

import com.ryans.entity.Account;
import com.ryans.mapper.AccountMapper;
import com.ryans.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author：Ryans
 * Date：Created in 2023/8/26 23:00
 * Introduction：
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public int update(Account account) {
        Account byUserId = accountMapper.getByUserId(account.getUserId());
        if (account.getMoney() > byUserId.getMoney()) {
            throw new RuntimeException("账户余额不足");
        }
        return accountMapper.updateByUserId(account.getUserId(), account.getMoney());
    }
}