package com.stupid.dev.entity.door.po;

import lombok.Data;

/**
 * 钥匙增量信息
 */
@Data
public class KeyIncInfo {

    /** 增量id */
    private Long id;
    /** 钥匙id */
    private String keyId;
    /** 钥匙类型 1门卡 2人脸 3密码 */
    private Short keyType;
    /** 钥匙值 */
    private String keyValue;
}
