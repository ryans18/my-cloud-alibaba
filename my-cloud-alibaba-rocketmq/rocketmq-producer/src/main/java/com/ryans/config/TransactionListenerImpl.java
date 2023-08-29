package com.ryans.config;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

/**
 * Author：Ryans
 * Date：Created in 2023/8/29 22:05
 * Introduction：
 */
@Component("myTransactionListener")
public class TransactionListenerImpl implements TransactionListener {

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object org) {
        // 业务处理逻辑
        String num = message.getProperty("test");
        if ("1".equals(num)) {
            System.out.println("execute: " + new String(message.getBody()) + " unknown");
            return LocalTransactionState.UNKNOW;  // unknown会进入下面的check消息
        } else if ("2".equals(num)) {
            System.out.println("execute: " + new String(message.getBody()) + " rollback");
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
        System.out.println("execute: " + new String(message.getBody()) + " commit");
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        // check事务有没有执行成功
        System.out.println("executer: " + new String(messageExt.getBody()));
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}