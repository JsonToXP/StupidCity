package com.stupid.common.mq.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 自定义事务监听器
 */
public class DefaultTransactionListener implements TransactionListener {

    /**
     * 回调方法
     * 消息预提交成功会触发该方法执行
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    /**
     * 回查方法
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
