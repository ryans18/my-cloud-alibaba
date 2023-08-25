package com.ryans.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ryans.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Author : Ryans
 * Date : 2023/8/25
 * Introduction :
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    @SentinelResource("queryUser")
    public String queryUser() {
        return "ryans";
    }
}
