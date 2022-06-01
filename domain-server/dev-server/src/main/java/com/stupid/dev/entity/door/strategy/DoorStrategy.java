package com.stupid.dev.entity.door.strategy;

import com.stupid.common.api.core.exception.DefaultServiceException;
import com.stupid.dev.entity.door.service.IDoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 门禁机策略类
 * 策略模式
 */
@Component
public class DoorStrategy {

    private final List<IDoorService> doorServiceList;

    @Autowired
    public DoorStrategy(List<IDoorService> doorServiceList) {
        this.doorServiceList = doorServiceList;
    }

    /**
     * 根据类型获取对应的处理类
     */
    public IDoorService getDoorHandler(String productType){
        IDoorService doorProduct = doorServiceList.stream()
                .filter(service -> service.isSupport(productType))
                .findFirst().orElse(null);
        if(doorProduct != null){
            return doorProduct;
        }else {
            throw new DefaultServiceException("不存在改类型的设备");
        }
    }
}
