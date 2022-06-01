package com.stupid.dev.entity.door.service;

import com.stupid.dev.entity.door.po.DoorInfo;
import com.stupid.dev.entity.door.po.KeyIncInfo;
import com.stupid.dev.entity.door.po.OpenLogInfo;

import java.util.List;

/**
 * 门禁机业务接口
 */
public interface IDoorService {

    /**
     * 判断设备类型及版本号
     */
    boolean isSupport(String productType);

    /**
     * 新增门禁设备
     */
    String saveDoor(DoorInfo doorInfo);

    /**
     * 远程开门
     */
    String openDoor(Long doorId);

    /**
     * 远程重启
     */
    String rebootDoor(Long doorId);

    /**
     * 钥匙下发
     * HJ v1.0采用此方式
     */
    String pushKeyList(List<KeyIncInfo> keyList);

    /**
     * 拉取钥匙增量
     * HJ v2.0采用此方式
     */
    List<KeyIncInfo> pullKeyList(Long doorId);

    /**
     * 日志上传 http
     * HJ v2.0采用此方式
     */
    String uploadOpenLogInfo(OpenLogInfo openLogInfo);
}
