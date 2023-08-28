package com.ryans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

/**
 * Author：Ryans
 * Date：Created in 2023/8/28 23:11
 * Introduction：
 */
@SpringBootApplication
public class RocketMQConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMQConsumerApplication.class, args);
    }

    // 新版本不能使用Listener的方式接收消息，而是使用函数式接口接收消息
    // 方法名：consumer 对应配置文件中的spring.cloud.stream.function.definition
    @Bean
    public Consumer<Message<String>> consumer() {
        return msg -> {
            System.out.println("收到消息： " + msg.getPayload());
        };
    }
}