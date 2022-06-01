package com.stupid.dev.entity.door.decorator;

import com.stupid.dev.entity.door.handler.AbstractDoorHandler;
import com.stupid.dev.entity.door.po.DoorInfo;
import com.stupid.dev.entity.door.po.KeyIncInfo;
import com.stupid.dev.entity.door.po.OpenLogInfo;
import com.stupid.dev.entity.door.service.IDoorService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 门禁机处理类装饰器
 * 装饰器模式
 * 适合针对多个厂商同时对原有方法做增强
 */
@Slf4j
public class DoorHandlerDecorator extends AbstractDoorHandler {

    private IDoorService doorService;

    public DoorHandlerDecorator(IDoorService doorService) {
        this.doorService = doorService;
    }

    @Override
    public boolean isSupport(String productType) {
        return doorService.isSupport(productType);
    }

    @Override
    public String saveDoor(DoorInfo doorInfo) {
        return doorService.saveDoor(doorInfo);
    }

    @Override
    public String openDoor(Long doorId) {
        return doorService.openDoor(doorId);
    }

    @Override
    public String rebootDoor(Long doorId) {
        return doorService.rebootDoor(doorId);
    }

    @Override
    public String pushKeyList(List<KeyIncInfo> keyList) {
        return doorService.pushKeyList(keyList);
    }

    @Override
    public List<KeyIncInfo> pullKeyList(Long doorId) {
        return doorService.pullKeyList(doorId);
    }

    @Override
    public String uploadOpenLogInfo(OpenLogInfo openLogInfo) {
        return doorService.uploadOpenLogInfo(openLogInfo);
    }

    /**
     * 增强save方法
     */
    public String saveDoorV1_1(DoorInfo doorInfo) {
        log.info("前置业务v1.1");
        String resp = doorService.saveDoor(doorInfo);
        log.info("后置业务v1.1");
        return resp;
    }
    public String saveDoorV1_2(DoorInfo doorInfo) {
        log.info("前置业务v1.2");
        String resp = doorService.saveDoor(doorInfo);
        log.info("后置业务v1.2");
        return resp;
    }
}
