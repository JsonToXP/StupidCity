package com.stupid.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * 注册过滤器
 */
@Component
public class TokenBucketFilterFactory extends AbstractGatewayFilterFactory {

    @Autowired
    private TokenBucketLimitFilter tokenBucketLimitFilter;

    /**
     * 注册过滤器
     */
    @Override
    public GatewayFilter apply(Object config) {
        return tokenBucketLimitFilter;
    }

    /**
     * 给过滤器起名
     */
    @Override
    public String name() {
        return "TokenBucketLimiter";
    }


}
