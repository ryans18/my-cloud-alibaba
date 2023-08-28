package com.ryans.controller;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Author：Ryans
 * Date：Created in 2023/8/28 23:05
 * Introduction：
 */
@RestController
public class TestController {

    @Autowired
    private StreamBridge streamBridge;  // 新版本不用RocketMQ的Listener监听消息，而是使用StreamBridge
    @GetMapping("/testSendMsg")
    public String sendMsg(String msg) {
        Map<String, Object> header = new HashMap<>();
        header.put(MessageConst.PROPERTY_TAGS, "tagStr");
        Message<String> message = MessageBuilder.createMessage(msg, new MessageHeaders(header));
        streamBridge.send("producer-out-0", message);
        return JSON.toJSONString(message);
    }
}