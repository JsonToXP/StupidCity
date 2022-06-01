package com.stupid.dev.infrastructure.toolkit;

import com.stupid.dev.entity.door.po.DoorInfo;
import com.stupid.common.api.dev.vo.DoorVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


/**
 * bean转换工具类
 */
@Mapper(componentModel = "spring")
public interface IBeanConvert {

    @Mappings({
            @Mapping(target = "state",ignore = true),
            @Mapping(target = "createTime",ignore = true),
            @Mapping(target = "activeTime",ignore = true),
            @Mapping(target = "doorExtras.remark",source = "remark")
    })
    DoorInfo doorVO2PO(DoorVO doorVO);


    @Mappings({
            @Mapping(target = "state",expression = "java(com.stupid.common.api.dev.dict.DoorStateEnum.getDesc(doorInfo.getState()))"),
            @Mapping(target = "createTime",expression = "java(DateToolkit.formatDate(doorInfo.getCreateTime()))"),
            @Mapping(target = "activeTime",expression = "java(DateToolkit.formatDate(doorInfo.getActiveTime()))")
    })
    DoorVO doorPO2VO(DoorInfo doorInfo);



}
