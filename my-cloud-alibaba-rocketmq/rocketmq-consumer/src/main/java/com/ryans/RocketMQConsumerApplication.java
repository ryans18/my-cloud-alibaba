package com.ryans;

import com.alibaba.cloud.stream.binder.rocketmq.support.RocketMQMessageConverterSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Author：Ryans
 * Date：Created in 2023/8/28 23:11
 * Introduction：
 */
@SpringBootApplication
@Slf4j
public class RocketMQConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMQConsumerApplication.class, args);
    }

    // 新版本不能使用Listener的方式接收消息，而是使用函数式接口接收消息
    // 方法名：consumer 对应配置文件中的spring.cloud.stream.function.definition
    @Bean
    public Consumer<Message<String>> consumer() {
        return msg -> {
            String tagHeaderKey = RocketMQMessageConverterSupport.toRocketHeaderKey(MessageConst.PROPERTY_TAGS);
            System.out.println(" 收到消息: " + msg.getPayload() + " TAG:" +
                    msg.getHeaders().get(tagHeaderKey).toString());
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        };
    }

    @Bean
    public Consumer<Message<String>> txConsumer() {
        return msg -> {
            Object arg = msg.getHeaders();
            log.info("收到消息: " + msg.getPayload() + " ARG:"
                    + arg.toString());
        };
    }
    @Bean
    public Consumer<Message<String>> sqlConsumer() {
        return msg -> {
            String colorHeaderKey = "color";
            String priceHeaderKey = "price";
            log.info("tag收到消息: " + msg.getPayload() + " COLOR:" +
                    msg.getHeaders().get(colorHeaderKey).toString() + " " +
                    "PRICE: " + msg.getHeaders().get(priceHeaderKey).toString());
        };
    }
    @Bean
    public Consumer<Message<String>> tagConsumer() {
        return msg -> {
            Object arg = msg.getHeaders();
            log.info("tag收到消息: " + msg.getPayload() + " ARG:"
                    + arg.toString());
        };
    }
}