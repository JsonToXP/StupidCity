package com.stupid.dev.dao.mapper;

import com.stupid.dev.entity.door.po.DoorInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IDoorInfoMapper {

    /**
     * 添加门禁机
     */
    int insertDoorInfo(DoorInfo doorInfo);

    /**
     * 查询设备详情
     */
    DoorInfo selectDoorInfoById(Long id);

    /**
     * 更新心跳时间
     */
    int updateDoorActiveTime(Long id);

    /**
     * 查询设备号
     */
    @Select("select dev_code\n" +
            "from door_info\n" +
            "where id = #{id}")
    String selectDevCodeById(Long id);

}
