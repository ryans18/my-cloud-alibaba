package com.ryans.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ryans.entity.User;
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

    @Override
    public User getUser(Long id) {
        User user = null;
        if (id == 1) {
            user = new User(1L, "张三", 19, "西安市雁塔区");
        } else if (id == 2) {
            user = new User(3L, "李四", 19, "西安市未央区");
        } else if (id == 3) {
            user = new User(3L, "王五", 20, "西安市碑林区");
        }
        return user;
    }
}
