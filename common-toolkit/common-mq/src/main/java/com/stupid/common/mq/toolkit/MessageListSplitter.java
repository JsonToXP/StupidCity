package com.stupid.common.mq.toolkit;

import org.apache.rocketmq.common.message.Message;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 消息列表分割器
 * 小于等于4M
 */
public class MessageListSplitter implements Iterator<List<Message>> {

    /** 极限值4M */
    private final int SIZE_LIMIT = 4 * 1024 * 1024;
    /** 批量发送消息的全部 */
    private final List<Message> messages;
    /** 批量发送消息的小集合的起始索引 */
    private int currIndex = 0;

    public MessageListSplitter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean hasNext() {
        // 当前遍历的消息集合的起始索引要小于消息总数
        return currIndex < messages.size();
    }

    @Override
    public List<Message> next() {
        int nextIndex = currIndex;
        // 当前要发送的小批量消息的大小
        int totalSize = 0;
        for (; nextIndex < messages.size(); nextIndex++){
            Message message = messages.get(nextIndex);
            int tmpSize = message.getTopic().length() + message.getBody().length;
            Map<String, String> properties = message.getProperties();
            for (Map.Entry<String,String> entry:
                 properties.entrySet()) {
                tmpSize += entry.getKey().length() + entry.getValue().length();
            }
            tmpSize = totalSize + 20;
            // 当前消息本身就大于4M
            if(tmpSize > SIZE_LIMIT){
                if(nextIndex - currIndex == 0){
                    nextIndex ++;
                }
                break;
            }

            if(tmpSize + totalSize > SIZE_LIMIT){
                break;
            }else {
                totalSize += tmpSize;
            }
        }

        // 获取当前messages列表的子集合 [currIndex, nextIndex)
        List<Message> subList = this.messages.subList(currIndex, nextIndex);
        // 下次遍历的起始索引
        currIndex = nextIndex;
        return subList;
    }
}
