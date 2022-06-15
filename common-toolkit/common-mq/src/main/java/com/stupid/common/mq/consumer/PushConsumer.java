package com.stupid.common.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Slf4j
public class PushConsumer {

    private static final String CG_NAME = "cg";

    private static final String SUB_TOPIC = "Topic001";

    @PostConstruct
    public void init() throws Exception{
        // 定义一个pull消费者组
        //DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("cg")
        // 定义一个push消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(CG_NAME);
        // 一个消费组需要指定不同的实例名
        consumer.setInstanceName("01");
        // 指定nameserver
        consumer.setNamesrvAddr("127.0.0.1:9876");
        // 指定从那一条消息开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        // 指定从哪个时间后开始消费
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
//        consumer.setConsumeTimestamp("2022060900"); //yyyyMMddHHmmss
        // todo 批量消费配置 设置批量拉取个数，默认32；问题，要么一起拉取成功，要么一个失败都失败；设置的越大，拉取时间越长
        //consumer.setPullBatchSize(24);
        // todo 批量消费配置 设置批量消费个数，默认1，最大32，因为每次最多拉取32条消息；问题，要么一起消费成功，一个消费是吧，就都失败，因为这一批数据都是一个消费线程处理的；设置的越大，并发消费能力越差
        //consumer.setConsumeMessageBatchMaxSize(4);
        // 指定消费的topic和tag
        consumer.subscribe(SUB_TOPIC,"*");
        // todo 基于用户属性 通过sql过滤筛选消息 复杂过滤
        // todo 需要修改Broker配置，开启sql过滤 enablePropertyFilter = true
        //consumer.subscribe("aaa", MessageSelector.bySql("age between 0 and 6"));
        // 指定消费模式，默认集群消费
        //consumer.setMessageModel(MessageModel.BROADCASTING);// 广播消费
        // 设置顺序消费失败重试间隔，默认1000ms，取值范围 10-30000
        //consumer.setSuspendCurrentQueueTimeMillis(100);
        // 设置无序消息重试次数，大于16次后的间隔时间均为2小时，CG下只修改一个C，则全CG生效；对个C修改，则覆盖之前的设置
        //consumer.setMaxReconsumeTimes(10);
        // 注册消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            // 当broker中有了订阅的消息就会触发该方法
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt ext:
                     list) {
                    log.info("Instance01接受MQ消息，{}",ext);
                }
                // 返回当前comsumer消费的状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                // 消费异常返回
                //return ConsumeConcurrentlyStatus.RECONSUME_LATER;

                // todo 如果需要消费重试 3种方式
                //return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                //return null;
                //throw new RuntimeException();
            }
        });
        // 启动消费者
        consumer.start();

//        DefaultMQPushConsumer consumer2 = new DefaultMQPushConsumer(CG_NAME);
//        consumer2.setInstanceName("02");
//        consumer2.setNamesrvAddr("127.0.0.1:9876");
//        consumer2.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
//        consumer2.subscribe(SUB_TOPIC,"*");
//        consumer2.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                for (MessageExt ext:
//                        list) {
//                    log.info("Instance02接受MQ消息，{}",ext);
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//        consumer2.start();
    }

}
