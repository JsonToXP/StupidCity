package com.stupid.common.redis.entity.dict;

public enum LuaScriptEnum {

    INIT_TOKEN_BUCKET("initTokenBucket","初始化令牌桶"),
    GET_TOKEN("getToken","获取令牌");

    private String type;
    private String desc;

    LuaScriptEnum(String type, String desc) {
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
