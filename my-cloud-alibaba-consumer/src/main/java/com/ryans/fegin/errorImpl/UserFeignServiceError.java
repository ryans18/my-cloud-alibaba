package com.ryans.fegin.errorImpl;

import com.ryans.fegin.UserFeignService;
import org.springframework.stereotype.Component;

/**
 * Author：Ryans
 * Date：Created in 2023/8/27 21:44
 * Introduction：
 */
@Component
public class UserFeignServiceError implements UserFeignService {

    @Override
    public String say(String name) {
        return "服务繁忙，请稍后重试";
    }
}