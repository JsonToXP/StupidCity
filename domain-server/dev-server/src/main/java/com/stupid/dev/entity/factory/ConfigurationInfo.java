package com.stupid.dev.entity.factory;

import lombok.Data;

/**
 * 配置信息
 */
@Data
public class ConfigurationInfo {

    /** 服务器地址 */
    private String host;
    /** 服务器端口 */
    private Integer port;
}
