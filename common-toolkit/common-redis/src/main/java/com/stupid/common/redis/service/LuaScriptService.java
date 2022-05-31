package com.stupid.common.redis.service;

import com.stupid.common.redis.entity.dict.LuaScriptEnum;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 加载lua脚本
 */
@Component
public class LuaScriptService {

    /**
     * 单例模式
     */
    private DefaultRedisScript<Long> initTokenBucketScript;
    private DefaultRedisScript<Long> getTokenScript;

    /**
     * 初始化加载脚本
     */
    @PostConstruct
    public void init(){
        // 令牌桶初始化脚本
        // 创建lua脚本对象
        DefaultRedisScript<Long> initTokenBucketScript = new DefaultRedisScript();
        // 加载lua脚本 ClassPathResource读取类路径（mvn编译后的target/classes目录）下的lua脚本
        initTokenBucketScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/InitTokenBucket.lua")));
        // 设置lua脚本返回值
        initTokenBucketScript.setResultType(Long.class);
        this.initTokenBucketScript = initTokenBucketScript;

        // 获取令牌脚本
        DefaultRedisScript<Long> getTokenScript = new DefaultRedisScript<>();
        getTokenScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("luascript/GetTokenBucket.lua")));
        getTokenScript.setResultType(Long.class);
        this.getTokenScript = getTokenScript;
    }

    /**
     * 获取lua对象
     */
    public DefaultRedisScript getLuaScript(LuaScriptEnum luaScriptEnum){
        switch (luaScriptEnum){
            case INIT_TOKEN_BUCKET:
                return initTokenBucketScript;
            case GET_TOKEN:
                return getTokenScript;
            default:
                return null;
        }
    }

}
