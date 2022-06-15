package com.stupid.common.mq.producer;

import com.alibaba.fastjson.JSONObject;
import com.stupid.common.core.toolkit.ThreadPoolToolkit;
import com.stupid.common.mq.transaction.DefaultTransactionListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class TranscationMsgProducer {

    private static TransactionMQProducer producer;

    /**
     * 初始化生产者
     */
    @PostConstruct
    public void init() throws Exception{
        // 创建生产者组
        TransactionMQProducer producer = new TransactionMQProducer("pg");
        // 设置nameserver地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 设置发送失败时重新发送的次数，默认2次
        producer.setRetryTimesWhenSendFailed(3);
        // 设置发送超时时限，默认3s
        producer.setSendMsgTimeout(5000);
        // 指定新创建的Topic的Queue的数量，默认为4
        producer.setDefaultTopicQueueNums(2);
        // todo 事务消息 为生产者指定线程池
        producer.setExecutorService(ThreadPoolToolkit.executorService);
        // todo 事务消息 为生产者添加事务监听器
        producer.setTransactionListener(new DefaultTransactionListener());
        // 启动生产者
        producer.start();
        TranscationMsgProducer.producer = producer;
    }

    /**
     * 发送事务消息
     */
    public static Boolean sendTranscationMsg(String topic,String tag,String key,Object msgObj){
        byte[] body = JSONObject.toJSONString(msgObj).getBytes();
        Message message = new Message(topic,tag,body);
        message.setKeys(key);

        try{
            // arg 本地事务所需要的参数
            TransactionSendResult result = producer.sendMessageInTransaction(message, null);
        }catch (Exception e){
            log.error("MQ事务消息发送失败，{}",e.getMessage());
            return false;
        }

        return true;
    }

}
