package com.stupid.dev.entity.door.handler.impl;

import com.stupid.common.api.dev.dict.DoorTypeEnum;
import com.stupid.dev.entity.door.po.DoorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 慧居门禁机
 * 版本v1.1
 * 协议socket
 * 静态继承
 * 适合针对单个厂商版本升级后对老版本方法做增强
 */
@Service
@Slf4j
public class HJv1_1DoorHandler extends HJv1_0DoorHandler {

    @Override
    public boolean isSupport(String productType) {
        return DoorTypeEnum.DOOR_TYPE_HJ_V1_1.getType().equals(productType);
    }

    @Override
    public String saveDoor(DoorInfo doorInfo) {
        log.info("前置业务v1.1");
        String resp = super.saveDoor(doorInfo);
        log.info("后置业务v1.1");
        return resp;
    }

    @Override
    public void checkDoor() {
        log.info("校验设备 v1.1 。。。");
    }
}
