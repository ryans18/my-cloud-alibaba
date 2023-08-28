package com.ryans.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Author : Ryans
 * Date : 2023/8/28
 * Introduction : GlobalFilter
 */
@Component
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.MyConfig> {

    @Override
    public GatewayFilter apply(MyConfig config) {
        return (((exchange, chain) -> {
            System.out.println("调用filter前: " + config.getName());
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("调用filter之后: " + config.getName());
            }));
        }));
    }
    public MyGatewayFilterFactory() {
        super(MyConfig.class);
    }
    public static class MyConfig {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
