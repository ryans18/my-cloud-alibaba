package com.ryans.controller;

import com.alibaba.cloud.stream.binder.rocketmq.constant.RocketMQConst;
import com.alibaba.fastjson.JSON;
import com.ryans.config.Constant;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
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
//    public static final String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
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
        for (int i = 0; i < 100; i++) {
            String key = "KEY" + i;
            String tag = Constant.TAGS[i % Constant.TAGS.length];
            Map<String, Object> headers = new HashMap<>();
            headers.put(MessageConst.PROPERTY_KEYS, key);
            headers.put(MessageConst.PROPERTY_TAGS, tag);
            headers.put(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID, i);
            Message<String> message = new GenericMessage<>(String.format("hello, 我是第%d个消息, Tag：%s",i, tag), headers);
            streamBridge.send("producer-out-0", message);
        }
        return "SUCCESS";
    }
    @GetMapping("/sendTxMsg")
    public String sendTxMsg(int num) {
        MessageBuilder<String> messageBuilder = MessageBuilder.withPayload("hello, Tx msg");
        messageBuilder.setHeader("test", String.valueOf(num))
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader(RocketMQConst.USER_TRANSACTIONAL_ARGS, "binder");
        Message<String> message = messageBuilder.build();
        System.out.println("send tx msg: " + message);
        streamBridge.send("producer-out-1", message);
        return "SUCCESS";
    }

    @GetMapping("/sendSqlMsg")
    public String sendSqlMsg() {
        MessageBuilder<String> builder = MessageBuilder.withPayload("hello, sql msg no consumer");
        builder.setHeader("color", "red")
                .setHeader("price", 4)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        Message<String> message = builder.build();
        System.out.println("send msg: " + message);
        streamBridge.send("producer-out-2", message);

        builder = MessageBuilder.withPayload("hello, sql msg has consumer");
        builder.setHeader("color", "blue")
                .setHeader("price", 5)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        message = builder.build();
        System.out.println("send msg: " + message);
        streamBridge.send("producer-out-2", message);

        builder = MessageBuilder.withPayload("hello, tag msg no consumer");
        builder.setHeader(MessageConst.PROPERTY_TAGS, "tag1")
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        message = builder.build();
        System.out.println("send msg: " + message);
        streamBridge.send("producer-out-2", message);

        builder = MessageBuilder.withPayload("hello, tag msg has consumer");
        builder.setHeader(MessageConst.PROPERTY_TAGS, "tag5")
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        message = builder.build();
        System.out.println("send msg: " + message);
        streamBridge.send("producer-out-2", message);
        return "SUCCESS";
    }

    @GetMapping("/sendBroadcastMsg")
    public String sendBroadcastMsg() {
        MessageBuilder<String> builder = MessageBuilder.withPayload("hello, broadcast msg");
        builder.setHeader("color", "red")
                .setHeader("price", 4)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        Message<String> message = builder.build();
        System.out.println("send msg: " + message);
        streamBridge.send("producer-out-2", message);
        return "SUCCESS";
    }
}