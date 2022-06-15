package com.stupid.common.mq.producer;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Slf4j
public class MsgProducer {

    private static DefaultMQProducer producer;

    /**
     * 初始化生产者
     */
    @PostConstruct
    public void init() throws Exception{
        // 创建生产者组
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        // 设置nameserver地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 设置发送失败时重新发送的次数，默认2次
        producer.setRetryTimesWhenSendFailed(3);
        // 异步消息发送失败重试
        //producer.setRetryTimesWhenSendAsyncFailed(0);
        // 设置发送超时时限，默认3s
        producer.setSendMsgTimeout(5000);
        // 指定新创建的Topic的Queue的数量，默认为4
        producer.setDefaultTopicQueueNums(16);
        // 设置发送消息最大上限，只设置不行，还需要修改broker，默认4M
        //producer.setMaxMessageSize(4 * 1024 * 1024);
        // 启动生产者
        producer.start();
        MsgProducer.producer = producer;
    }

    /**
     * 发送同步消息
     *
     * SendResult
     * [sendStatus=SEND_OK,
     * msgId=C0A8032E3CCC18B4AAC2226C4B080000,
     * offsetMsgId=AC12000400002A3B0000000000001A7A,
     * messageQueue=MessageQueue [topic=MySycnTopic, brokerName=broker02, queueId=1], queueOffset=4]
     *
     * sendStatus
     * - SEND_OK 发送成功
     * - FLUSH_DISK_TIMEOUT 刷盘超时，当broker刷盘策略为同步时，才会触发
     * - FLUSH_SLAVE_TIMEOUT 同步副本超时，当M-S复制策略为同步时，才会触发
     * - SLAVE_NOT_AVAILABLE 没有可用的副本，当M-S复制策略为同步时，才会触发
     *
     * 主从节点复制策略 同步 异步
     * brokerRole=SYNC_MASTER
     * 刷盘策略 同步 异步
     * flushDiskType=ASYNC_FLUSH
     *
     * msgId=C0A8032E3CCC18B4AAC2226C4B080000
     * - 由producer生成，producerIp + 进程pid + MessageClientIDSetter类的ClassLoader的hashcode + 当前时间 + AutomicInteger自增计数器
     * offsetMsgId=AC12000400002A3B0000000000001A7A
     * - 由broker生成，brokerIp + 物理分区offset(Queue中的偏移量)
     *
     *
     */
    public static Boolean sendMsgSync(String topic,String tag,String key,Object msgObj){
        byte[] body = JSONObject.toJSONString(msgObj).getBytes();
        Message message = new Message(topic,tag,body);
        // 为消息指定key
        message.setKeys(key);
        // todo 设置用户属性，用于SQL过滤
        //message.putUserProperty("orderCode",key);
        // 设置延迟等级 1 5 10 30 1 2 3 4 5 6 7 8 9 10 20 30 1 2
        //message.setDelayTimeLevel(3);
        try{
            SendResult result = producer.send(message);
            log.info("发送MQ同步消息，{}",result);
            if(result.getSendStatus().equals(SendStatus.SEND_OK)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            log.error("MQ同步消息发送失败，{}",e.getMessage());
            return false;
        }
    }

    /**
     * 异步消息发送
     */
    public static Boolean sendMsgAsync(String topic,String tag,String key,Object msgObj){
        byte[] body = JSONObject.toJSONString(msgObj).getBytes();
        Message message = new Message(topic,tag,body);
        // 为消息指定key
        message.setKeys(key);
        try{
            producer.send(message, new SendCallback() {
                // 当producer接受到MQ发送来的ACK后就会触发该回调方法
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("发送MQ异步消息，{}",sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    log.error("发送MQ异步消息失败，{}",throwable.getMessage());
                }
            });
        }catch (Exception e){
            log.error("MQ异步消息发送失败，{}",e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 发送单向消息
     */
    public static Boolean sendMsgOneway(String topic,String tag,String key,Object msgObj){
        byte[] body = JSONObject.toJSONString(msgObj).getBytes();
        Message message = new Message(topic,tag,body);
        // 为消息指定key
        message.setKeys(key);

        try{
            producer.sendOneway(message);
        }catch (Exception e){
            log.error("MQ单向消息发送失败，{}",e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * 同步发送分区有序消息 自定义Queue选择器
     */
    public static Boolean sendMsgSyncBySelect(String topic,String tag,String key,Object msgObj){
        byte[] body = JSONObject.toJSONString(msgObj).getBytes();
        Message message = new Message(topic,tag,body);
        message.setKeys(key);
        try{
            SendResult result = producer.send(message, new MessageQueueSelector() {
                // 自定义Queue选择策略 参数arg对应的是参数key
                @Override
                public MessageQueue select(List<MessageQueue> list, Message msg, Object arg) {
                    int queueId = arg.hashCode() % list.size();
                    return list.get(queueId);
                }
            }, key);
            log.info("MQ自定义分区同步消息发送,{}",result);
        }catch (Exception e){
            log.error("MQ自定义分区同步消息发送失败，{}",e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 异步发送分区有序消息 自定义Queue选择器
     */
    public static Boolean sendMsgAsyncBySelect(String topic,String tag,String key,Object msgObj){
        byte[] body = JSONObject.toJSONString(msgObj).getBytes();
        Message message = new Message(topic,tag,body);
        message.setKeys(key);
        try{
            producer.send(message, (list, msg, arg) -> {
                int queueId = arg.hashCode() % list.size();
                return list.get(queueId);
            }, key, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("发送MQ异步消息，{}",sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    log.error("发送MQ异步消息失败，{}",throwable.getMessage());
                }
            });
        }catch (Exception e){
            log.error("MQ自定义分区同步消息发送失败，{}",e.getMessage());
            return false;
        }
        return true;
    }

}
