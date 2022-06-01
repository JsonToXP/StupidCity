package com.stupid.dev.dao.mapper;

import com.stupid.dev.entity.door.po.DoorExtras;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IDoorExtrasMapper {

    /**
     * 添加门禁机附加信息
     */
    int insertDoorExtras(DoorExtras doorExtras);

}
