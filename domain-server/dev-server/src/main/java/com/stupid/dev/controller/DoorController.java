package com.stupid.dev.controller;

import com.stupid.common.api.core.pojo.ParamValidationGroup;
import com.stupid.common.api.core.vo.ResponseObject;
import com.stupid.dev.entity.vo.DoorVO;
import com.stupid.dev.service.IDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 门禁机API
 */
@RestController
@RequestMapping("door")
@Slf4j
public class DoorController {

    @Autowired
    private IDeviceService doorService;

    @Autowired
    private ServletWebServerApplicationContext applicationContext;

    /**
     * 查询端口号
     */
    @GetMapping("port")
    public String getPort(){
        return applicationContext.getWebServer().getPort()+"";
    }

    /**
     * 新增设备
     */
    @PostMapping("save")
    public ResponseObject<String> saveDoor(@RequestBody @Validated(ParamValidationGroup.Insert.class) DoorVO doorVO){
        log.info("saveDoor req : {}",doorVO);
        String resp = doorService.saveDoor(doorVO);
        return new ResponseObject<String>(resp);
    }

    /**
     * 根据id查门禁信息
     */
    @GetMapping("info/{id}")
    public ResponseObject<DoorVO> queryDoorInfo(@PathVariable Long id){
        log.info("queryDoorInfo req : {}",id);
        DoorVO doorVO = doorService.queryDoorInfo(id);
        return new ResponseObject(doorVO);
    }

    /**
     * 根据id查设备号
     */
    @GetMapping("devCode/{id}")
    public ResponseObject<String> queryDevCode(@PathVariable Long id){
        log.info("devCode req : {}",id);
        String resp = doorService.queryDevCode(id);
        return new ResponseObject<>(resp);
    }
}
