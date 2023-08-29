package com.ryans.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageHeaders;

import java.util.List;

/**
 * Author : Ryans
 * Date : 2023/8/29
 * Introduction :
 */
//@Configuration
@Slf4j
public class RocketConfig{
    @Bean
    public MessageQueueSelector messageQueueSelector() {
        return new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object arg) {
                Integer id = (Integer) ((MessageHeaders)arg).get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID);
                // 通过id选择分区
                int index = id % Constant.TAGS.length % list.size();
                log.info("select index: {} ", index);
                return list.get(index);
            }
        };
    }
}
