package com.ryans.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * Author : Ryans
 * Date : 2023/8/28
 * Introduction :
 */
@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("Global Filter 前");
        // 在这里可以进行自定义的全局过滤操作
        // 可以根据需要进行身份验证、请求日志记录等操作
        return chain.filter(exchange).thenEmpty(Mono.fromRunnable(() -> {
            System.out.println("Global Filter后");
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }

    // 应配置在@Configuration中
//    @Bean
//    @Order(0)
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            // 在这里可以进行自定义的全局过滤操作
            // 可以根据需要进行身份验证、请求日志记录等操作
            return chain.filter(exchange);
        };
    }
}
