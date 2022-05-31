package com.stupid.dev.service;


import com.stupid.dev.entity.vo.DoorVO;

public interface IDoorService {

    /**
     * 新增门禁机
     */
    String saveDoor(DoorVO doorVo);

    /**
     * 查询门禁详情
     */
    DoorVO queryDoorInfo(Long id);

    /**
     * 查设备号
     */
    String queryDevCode(Long id);
}
