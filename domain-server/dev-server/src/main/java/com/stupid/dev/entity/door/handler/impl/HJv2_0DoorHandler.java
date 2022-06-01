package com.stupid.dev.entity.door.handler.impl;

import com.stupid.common.api.dev.dict.DoorTypeEnum;
import com.stupid.dev.entity.door.po.DoorInfo;
import com.stupid.dev.entity.door.po.KeyIncInfo;
import com.stupid.dev.entity.door.po.OpenLogInfo;
import com.stupid.dev.entity.door.handler.AbstractHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 慧居门禁机
 * 版本v2.0
 * 协议socket
 */
@Service
@Slf4j
public class HJv2_0DoorHandler extends AbstractHandler {

    @Override
    public boolean isSupport(String productType) {
        return DoorTypeEnum.DOOR_TYPE_HJ_V2_0.getType().equals(productType);
    }

    @Override
    public String saveDoor(DoorInfo doorInfo) {
        return super.saveDoor(doorInfo);
    }

    @Override
    public void checkDoor() {
        log.info("校验设备 v2.0 。。。");
    }

    @Override
    public void feignBiz() {
        log.info("调用mqtt服务 v2.0 。。。");
    }

    @Override
    public String openDoor(Long doorId) {
        return super.openDoor(doorId);
    }

    @Override
    public void openDoorFeignBiz() {
        log.info("远程开门mqtt服务调用 v2.0 。。。");
    }

    @Override
    public String rebootDoor(Long doorId) {
        return super.rebootDoor(doorId);
    }

    @Override
    public void rebootDoorFeignBiz() {
        log.info("设备重启mqtt服务调用 v2.0 。。。");
    }

    @Override
    public List<KeyIncInfo> pullKeyList(Long doorId) {
        log.info("慧居门禁机v2.0拉取钥匙信息前置业务");
        return new ArrayList<>();
    }

    @Override
    public String uploadOpenLogInfo(OpenLogInfo openLogInfo) {
        log.info("慧居门禁机v2.0日志上传前置业务");
        return "success";
    }
}
