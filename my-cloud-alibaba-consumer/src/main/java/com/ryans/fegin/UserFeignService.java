package com.ryans.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author：Ryans
 * Date：Created in 2023/8/27 21:43
 * Introduction：
 */
// 指定服务名，需要loadbalancer， fallback可直接在服务报错时降级
@FeignClient(value = "provider-user", path = "hello", fallback = com.ryans.fegin.errorImpl.UserFeignServiceError.class)
public interface UserFeignService {

    @GetMapping("/say")
    String say(@RequestParam("name") String name);
}