package com.stupid.common.api.dev.dict;

public enum DoorTypeEnum {

    DOOR_TYPE_HJ_V1_0("hj_v1.0","慧居门禁v1.0"),
    DOOR_TYPE_HJ_V1_1("hj_v1.1","慧居门禁v1.1"),
    DOOR_TYPE_HJ_V2_0("hj_v2.0","慧居门禁v2.0"),
    DOOR_TYPE_DD_V1_0("dd_v1.0","多度门禁v1.0");

    private String type;
    private String desc;

    DoorTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
