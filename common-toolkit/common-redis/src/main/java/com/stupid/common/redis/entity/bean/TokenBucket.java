package com.stupid.common.redis.entity.bean;

import com.stupid.common.core.toolkit.SpringToolkit;
import com.stupid.common.redis.entity.dict.LuaScriptEnum;
import com.stupid.common.redis.service.LuaScriptService;
import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * 令牌桶领域对象
 * 充血模型
 */
@Data
public class TokenBucket {

    /** 令牌桶的key */
    private String key;
    /** 令牌桶最大token数 */
    private int maxToken;
    /** 令牌桶每秒生成的token数 */
    private int secToken;


    private RedisTemplate redisTemplate;
    private LuaScriptService luaScriptService;

    public TokenBucket(String key, int maxToken, int secToken) {
        this.key = key;
        this.maxToken = maxToken;
        this.secToken = secToken;

        this.redisTemplate = SpringToolkit.getBean("redisTemplate",RedisTemplate.class);
        this.luaScriptService = SpringToolkit.getBean(LuaScriptService.class);
        // 调用初始化方法
        init();
    }

    /**
     * 初始化令牌桶
     */
    private void init() {
        redisTemplate.execute(luaScriptService.getLuaScript(LuaScriptEnum.INIT_TOKEN_BUCKET),
                            Collections.singletonList(this.key),
                            this.maxToken,
                            this.secToken);
    }

    /**
     * 获取令牌
     * 传入令牌数，返回等待时间
     */
    public Long getToken(int tokens){
        // 返回微秒值
        Long waitTime = (Long) redisTemplate.execute(luaScriptService.getLuaScript(LuaScriptEnum.GET_TOKEN),
                Collections.singletonList(this.key),
                tokens);
        if(waitTime > 0){
            try {
                TimeUnit.MICROSECONDS.sleep(waitTime);
            }catch (InterruptedException e){
                e.getMessage();
            }
        }
        return waitTime;
    }

    /**
     * 获取令牌
     * 传入令牌数、超时时间，在超时时间内无法获取令牌，返回false
     */
    public Boolean getTokens(int tokens,long timeout,TimeUnit unit){
        // 返回微秒值
        Long waitTime = (Long) redisTemplate.execute(luaScriptService.getLuaScript(LuaScriptEnum.GET_TOKEN),
                Collections.singletonList(this.key),
                tokens,
                unit.toMicros(timeout));
        if(waitTime == -1){
            return false;
        }else if(waitTime > 0){
            try {
                TimeUnit.MICROSECONDS.sleep(waitTime);
            }catch (InterruptedException e){
                e.getMessage();
            }
        }
        return true;
    }

    /**
     * 获取令牌
     * 立刻获取，如需等待返回false
     */
    public Boolean getTokensNow(int tokens){
        return getTokens(tokens,0,TimeUnit.MICROSECONDS);
    }
}
