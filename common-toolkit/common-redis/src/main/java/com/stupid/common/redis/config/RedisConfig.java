package com.stupid.common.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    /**
     * RedisConnectionFactory
     * - JedisConnectionFactory
     * - LettuceConnectionFactory
     * - RedissonConnectionFactory
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedissonConnectionFactory factory){
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jsonSerializer = new Jackson2JsonRedisSerializer(Object.class);
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jsonSerializer.setObjectMapper(om);
        //key序列化
        template.setKeySerializer(stringSerializer);
        //value序列化
        template.setValueSerializer(jsonSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(jsonSerializer);
        return template;
    }

}
