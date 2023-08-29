package com.ryans.controller;

import com.alibaba.fastjson.JSON;
import com.ryans.config.Constant;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
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
    public static final String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
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
    @GetMapping("/testSendDelayMsg")
    public String sendDelayMsg(String msg) {
        // 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h 共18种延迟级别
        Map<String, Object> header = new HashMap<>();
        header.put(MessageConst.PROPERTY_TAGS, "tagStr");
        header.put(MessageConst.PROPERTY_DELAY_TIME_LEVEL, 4); // 30s
        Message<String> message = MessageBuilder.createMessage("delay：" +msg, new MessageHeaders(header));
        streamBridge.send("producer-out-0", message);
        return JSON.toJSONString(message);
    }
    @GetMapping("/sendOrderMsg")
    public String sendOrderMsg() {
        /*for (int i = 0; i < 100; i++) {
            String key = "KEY" + i;
            String tag = Constant.TAGS[i % Constant.TAGS.length];
            Map<String, Object> headers = new HashMap<>();
//            headers.put(MessageConst.PROPERTY_KEYS, key);
//            headers.put(MessageConst.PROPERTY_TAGS, tag);
//            headers.put(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID, i);
//            Message<String> message = new GenericMessage<>(String.format("hello, 我是第%d个消息, Tag：%s",i, tag));
            Message<String> message = new GenericMessage<>(String.format("hello, 我是第%d个消息",i));
            streamBridge.send("producer-out-0", message);
        }*/
        for (int i = 0; i < 100; i++) {
            String key = "KEY" + i;
            Map<String, Object> headers = new HashMap<>();
            headers.put(MessageConst.PROPERTY_KEYS, key);
            headers.put(MessageConst.PROPERTY_TAGS, tags[i % tags.length]);
            headers.put(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID, i);
            Message<String> msg = new GenericMessage("Hello RocketMQ " + i, headers);
            streamBridge.send("producer-out-0", msg);
        }
        return "SUCCESS";
    }
}