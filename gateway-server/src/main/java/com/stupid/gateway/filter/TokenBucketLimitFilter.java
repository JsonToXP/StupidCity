package com.stupid.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.stupid.common.api.core.vo.ResponseObject;
import com.stupid.common.redis.entity.bean.TokenBucket;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 令牌桶限流过滤器
 * 局部过滤器
 */
@Component
@Slf4j
public class TokenBucketLimitFilter implements GatewayFilter {

    private Map<String, TokenBucket> bucketMap = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 格局URL限流
        // 获取当前请求URL
        ServerHttpRequest request = exchange.getRequest();
        String requestPath = request.getPath().value();
        //log.info("请求路径：{}",requestPath);

        // 如果map中，key不存在，则调用第二个参数的方法初始化一个值，放入当前的key中，如果存在直接返回
//        TokenBucket tokenBucket = bucketMap.computeIfAbsent(requestPath, new Function<String, TokenBucket>() {
//            @Override
//            public TokenBucket apply(String s) {
//                return new TokenBucket(requestPath,500,500);
//            }
//        });
        TokenBucket tokenBucket = bucketMap.computeIfAbsent(requestPath, s -> new TokenBucket(s, 5, 1));
        // 领取令牌
        Boolean flag = tokenBucket.getTokensNow(2);
        if(flag){
            log.info("申请到令牌，放行");
            // 请求放行
            return chain.filter(exchange);
        }
        // 没拿到令牌
        log.info("未申请到令牌，返回");
        ResponseObject<String> resp = new ResponseObject<>(false,"系统繁忙，稍后再试");
        ServerHttpResponse response = exchange.getResponse();
        DataBuffer wrap = response.bufferFactory().wrap(JSON.toJSONBytes(resp));
        Mono<Void> mono = response.writeWith(Mono.just(wrap));
        return mono;
    }
}
