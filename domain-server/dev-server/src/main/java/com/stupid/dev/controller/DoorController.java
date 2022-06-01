package com.stupid.dev.controller;

import com.stupid.common.api.core.vo.ResponseObject;
import com.stupid.dev.entity.door.po.DoorInfo;
import com.stupid.dev.entity.door.po.KeyIncInfo;
import com.stupid.common.api.dev.vo.DoorVO;
import com.stupid.dev.entity.door.service.IDoorService;
import com.stupid.dev.entity.door.strategy.DoorStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("door")
@Slf4j
public class DoorController {

    private final DoorStrategy doorStrategy;

    @Autowired
    public DoorController(DoorStrategy doorStrategy) {
        this.doorStrategy = doorStrategy;
    }

    /**
     * 保存门禁机
     */
    @PostMapping("{doorType}/save")
    public ResponseObject<String> saveDoor(@RequestBody DoorVO doorVO,@PathVariable String doorType){
        IDoorService doorHandler = doorStrategy.getDoorHandler(doorType);
        String resp = doorHandler.saveDoor(new DoorInfo());
        return new ResponseObject<>(resp);
    }

    /**
     * 远程开门
     */
    @GetMapping("{doorType}/open/{doorId}")
    public ResponseObject<String> openDoor(@PathVariable(name = "doorType") String doorType,
                                           @PathVariable(name = "doorId") Long doorId){

        IDoorService doorHandler = doorStrategy.getDoorHandler(doorType);
        String resp = doorHandler.openDoor(doorId);
        return new ResponseObject<>(resp);
    }

    /**
     * 远程重启
     */
    @GetMapping("{doorType}/reboot/{doorId}")
    public ResponseObject<String> rebootDoor(@PathVariable(name = "doorType") String doorType,
                                             @PathVariable(name = "doorId") Long doorId){

        IDoorService doorHandler = doorStrategy.getDoorHandler(doorType);
        String resp = doorHandler.rebootDoor(doorId);
        return new ResponseObject<>(resp);
    }

    /**
     * 推送钥匙信息
     */
    @PostMapping("{doorType}/pushKey")
    public ResponseObject<String> pushKey(@PathVariable(name = "doorType") String doorType){
        IDoorService doorHandler = doorStrategy.getDoorHandler(doorType);
        String resp = doorHandler.pushKeyList(new ArrayList<>());
        return new ResponseObject<>(resp);
    }

    /**
     * 拉取钥匙信息
     */
    @PostMapping("{doorType}/pullKey")
    public ResponseObject<List> pullKey(@PathVariable(name = "doorType") String doorType){
        IDoorService doorHandler = doorStrategy.getDoorHandler(doorType);
        List<KeyIncInfo> resp = doorHandler.pullKeyList(0L);
        return new ResponseObject<>(resp);
    }

}
