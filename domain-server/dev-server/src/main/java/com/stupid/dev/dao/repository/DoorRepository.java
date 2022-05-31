package com.stupid.dev.dao.repository;

import com.stupid.common.redis.service.RedisService;
import com.stupid.dev.dao.mapper.IDoorExtrasMapper;
import com.stupid.dev.dao.mapper.IDoorInfoMapper;
import com.stupid.dev.entity.po.DoorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 门禁机仓库
 */
@Component
@Slf4j
public class DoorRepository {

    @Autowired
    private RedisService redisService;

    @Autowired
    private IDoorInfoMapper doorInfoMapper;

    @Autowired
    private IDoorExtrasMapper doorExtrasMapper;

    /**
     * 添加门禁机
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public int saveDoor(DoorInfo doorInfo){
        doorInfoMapper.insertDoorInfo(doorInfo);
        doorExtrasMapper.insertDoorExtras(doorInfo.getDoorExtras());
        return 1;
    }

    /**
     * 查询门禁详情
     */
    public DoorInfo queryDoorInfo(Long id){
        DoorInfo doorInfo = doorInfoMapper.selectDoorInfoById(id);
        return doorInfo;
    }

    /**
     * 查设备号
     */
    public String queryDevCode(Long id){
        String devCode = redisService.getString(id.toString());
        if(StringUtils.isEmpty(devCode)){
            log.info("redis中没有，从数据库查");
            devCode = doorInfoMapper.selectDevCodeById(id);
            redisService.setString(id.toString(),devCode,60);
        }
        return devCode;
    }
}
