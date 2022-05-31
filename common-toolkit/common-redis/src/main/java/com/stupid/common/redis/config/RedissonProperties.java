package com.stupid.common.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonProperties {

    private String address;
    private String host;
    private Integer port;
    private Integer database;

    public String getAddress() {
        StringBuffer sb = new StringBuffer("redis://");
        sb.append(host).append(":").append(port);
        return sb.toString();
    }
}
