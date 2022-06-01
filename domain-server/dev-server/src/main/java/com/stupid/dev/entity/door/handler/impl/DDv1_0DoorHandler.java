package com.stupid.dev.entity.door.handler.impl;

import com.stupid.common.api.dev.dict.DoorTypeEnum;
import com.stupid.dev.entity.door.decorator.DoorHandlerDecorator;
import com.stupid.dev.entity.door.handler.AbstractDoorHandler;
import com.stupid.dev.entity.door.po.DoorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 多度门禁机
 * 版本v1.0
 * 协议socket
 */
@Component
@Slf4j
public class DDv1_0DoorHandler extends AbstractDoorHandler {

    @Override
    public boolean isSupport(String productType) {
        return DoorTypeEnum.DOOR_TYPE_DD_V1_0.getType().equals(productType);
    }

    @Override
    public String saveDoor(DoorInfo doorInfo) {
        return super.saveDoor(doorInfo);
    }

    @Override
    public void checkDoor() {
        log.info("多度设备在线检查。。。");
    }

    @Override
    public void preBiz() {
        super.preBiz();
    }

    @Override
    public void postBiz() {
        log.info("多度后置任务");
    }

    @Override
    public void feignBiz() {
        log.info("多度远程设备服务调用");
    }

    @Override
    public String openDoor(Long doorId) {
        return super.openDoor(doorId);
    }

    @Override
    public void openDoorFeignBiz() {
        log.info("多度远程开门服务调用");
    }

    @Override
    public String rebootDoor(Long doorId) {
        return super.rebootDoor(doorId);
    }

    @Override
    public void rebootDoorFeignBiz() {
        log.info("多度设备重启服务调用");
    }
}
