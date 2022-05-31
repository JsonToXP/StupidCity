package com.stupid.dev.entity.factory;

import lombok.Data;

/**
 * 协议信息
 */
@Data
public class ProtocolInfo {

    /** 协议类型 socket websocket mqtt http */
    private String type;
    /** 协议编码 1 2 3 4 */
    private Integer code;

}
