package com.stupid.dev.entity.po;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DoorInfo {

    private Long id;
    private String name;
    private String devCode;
    private Short state;
    /** 最后心跳时间 */
    private Timestamp activeTime;
    private String createUser;
    private Timestamp createTime;

    /** 门禁附加信息表 */
    private DoorExtras doorExtras = new DoorExtras();

    public void setId(Long id) {
        this.id = id;
        this.doorExtras.setId(id);
    }
}
