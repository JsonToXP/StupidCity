package com.stupid.dev.entity.door.handler.impl;

import com.stupid.common.api.dev.dict.DoorTypeEnum;
import com.stupid.dev.entity.door.po.DoorInfo;
import com.stupid.dev.entity.door.po.KeyIncInfo;
import com.stupid.dev.entity.door.handler.AbstractHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 慧居门禁机
 * 版本v1.0
 * 协议socket
 */
@Service
@Slf4j
public class HJv1_0DoorHandler extends AbstractHandler {

    /**
     * 策略判断
     */
    @Override
    public boolean isSupport(String productType) {
        return DoorTypeEnum.DOOR_TYPE_HJ_V1_0.getType().equals(productType);
    }

    /**
     * 保存门禁机
     */
    @Override
    public String saveDoor(DoorInfo doorInfo) {
        return super.saveDoor(doorInfo);
    }

    /**
     * 校验门禁机
     */
    @Override
    public void checkDoor() {
        log.info("校验设备 v1.0 。。。");
    }

    /**
     * 保存门禁机需要调用的服务
     */
    @Override
    public void feignBiz() {
        log.info("调用netty服务 v1.0 。。。");
    }

    @Override
    public String openDoor(Long doorId) {
        return super.openDoor(doorId);
    }

    @Override
    public void openDoorFeignBiz() {
        log.info("远程开门netty服务调用 v1.0 。。。");
    }

    @Override
    public String rebootDoor(Long doorId) {
        return super.rebootDoor(doorId);
    }

    @Override
    public void rebootDoorFeignBiz() {
        log.info("设备重启netty服务调用 v1.0 。。。");
    }

    @Override
    public String pushKeyList(List<KeyIncInfo> keyList) {
        log.info("慧居门禁机v1.0推送钥匙信息前置业务");
        log.info("调用设备连接服务，发送socket消息");
        return "success";
    }
}
