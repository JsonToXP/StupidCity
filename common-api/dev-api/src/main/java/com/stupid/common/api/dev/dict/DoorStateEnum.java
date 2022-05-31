package com.stupid.common.api.dev.dict;


/**
 * 门禁状态枚举
 */
public enum DoorStateEnum {

    DOOR_STATE_ONLINE(1,"在线"),
    DOOR_STATE_OFFLINE(0,"离线");

    private Integer code;
    private String desc;

    public static String getDesc(int code){
        for (DoorStateEnum numEnum : DoorStateEnum.values()) {
            if (numEnum.getCode()==(code)) {
                return numEnum.getDesc();
            }
        }
        return DOOR_STATE_OFFLINE.getDesc();
    }

    public static Integer getCode(String desc){
        for (DoorStateEnum numEnum : DoorStateEnum.values()) {
            if (numEnum.getDesc().equals(desc)) {
                return numEnum.getCode();
            }
        }
        return DOOR_STATE_OFFLINE.getCode();
    }

    DoorStateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
