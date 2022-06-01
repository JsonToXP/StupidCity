package com.stupid.dev.entity.door.handler;

import com.stupid.dev.entity.door.po.DoorInfo;
import com.stupid.dev.entity.door.po.KeyIncInfo;
import com.stupid.dev.entity.door.po.OpenLogInfo;
import com.stupid.dev.entity.door.service.IDoorService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 模板设计模式
 * 源码例子：AbstractQueuedSynchronized
 * 适合对全部厂商原有方法做增强
 */
@Slf4j
public class AbstractDoorHandler implements IDoorService {

    @Override
    public boolean isSupport(String productType) {
        return false;
    }

    @Override
    public String saveDoor(DoorInfo doorInfo) {
        /**
         * 如果子类没有重写该方法，则调用时会抛出该异常，表示不知此方法
         */
        checkDoor();
        preBiz();
        postBiz();
        feignBiz();
        return "success";
    }

    public void checkDoor(){
        log.info("校验设备。。。");
    }

    public void preBiz(){
        log.info("前置业务。。。");
    }

    public void postBiz(){
        log.info("后置业务。。。");
    }

    public void feignBiz(){
        log.info("远程服务调用。。。");
    }

    /**
     * 远程开门
     */
    @Override
    public String openDoor(Long doorId) {
        log.info("远程开门前置业务。。。");
        openDoorFeignBiz();
        return "success";
    }

    public void openDoorFeignBiz(){
        log.info("远程开门服务调用。。。");
    }

    @Override
    public String rebootDoor(Long doorId) {
        log.info("设备重启前置业务。。。");
        rebootDoorFeignBiz();
        return "success";
    }

    public void rebootDoorFeignBiz(){
        log.info("设备重启服务调用。。。");
    }

    @Override
    public String pushKeyList(List<KeyIncInfo> keyList) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<KeyIncInfo> pullKeyList(Long doorId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String uploadOpenLogInfo(OpenLogInfo openLogInfo) {
        throw new UnsupportedOperationException();
    }

}
