package com.stupid.dev.entity.door.po;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 开门日志信息
 */
@Data
public class OpenLogInfo {

    /** 开门类型 1门卡 2人脸 3密码 4远程 */
    private Short openType;
    /** 开门时间 */
    private Timestamp openTime;
    /** 抓拍照片 */
    private String photoUrl;
}
