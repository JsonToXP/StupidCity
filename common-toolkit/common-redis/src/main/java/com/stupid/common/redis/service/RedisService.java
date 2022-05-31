package com.stupid.common.redis.service;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;


    /**
     * String set
     */
    public boolean setString(String key, String value, long seconds){
        redisTemplate.opsForValue().set(key,value,seconds, TimeUnit.SECONDS);
        return true;
    }

    /**
     * String get
     */
    public String getString(String key){
        Object o = redisTemplate.opsForValue().get(key);
        if(StringUtils.isEmpty(o)){
            return null;
        }
        return o.toString();
    }

    /**
     * 执行lua脚本
     */
    public Object execute(DefaultRedisScript script, List keys,Object...args){
        Object o = redisTemplate.execute(script, keys, args);
        return o;
    }

    //======================= 可重入锁 ==============================

    /**
     * 加锁
     * 获取不到阻塞等待
     */
    public Boolean lock(String lockKey){
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return true;
    }

    /**
     * 加锁 指定过期时间
     * 获取不到阻塞等待
     */
    public Boolean lock(String lockKey,long secondsTime){
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(secondsTime,TimeUnit.SECONDS);
        return true;
    }

    /**
     * 加锁
     * 获取不到返回false
     */
    public Boolean tryLock(String lockKey){
        RLock lock = redissonClient.getLock(lockKey);
        return lock.tryLock();
    }

    /**
     * 加锁 指定过期时间
     * 获取不到返回false
     */
    public Boolean tryLock(String lockKey,long secondsTime){
        RLock lock = redissonClient.getLock(lockKey);
        try{
            return lock.tryLock(secondsTime,TimeUnit.SECONDS);
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 加锁 指定过期时间 最大等待时间
     * 超过等待时间内返回false
     */
    public Boolean tryLockWait(String lockKey,long secondsTime,long timeout){
        RLock lock = redissonClient.getLock(lockKey);
        try{
            return lock.tryLock(timeout,secondsTime,TimeUnit.SECONDS);
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 释放锁
     */
    public Boolean unlock(String lockKey){
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
        return true;
    }

    //================================ 公平锁 ==================================
    /**
     * 公平锁
     */
    public Boolean fairLock(String lockKey){
        RLock fLock = redissonClient.getFairLock(lockKey);
        fLock.lock();
        return true;
    }

    public Boolean fairLock(String lockKey,long secondsTime){
        RLock fLock = redissonClient.getFairLock(lockKey);
        fLock.lock(secondsTime,TimeUnit.SECONDS);
        return true;
    }

    public Boolean fairTryLock(String lockKey){
        RLock fLock = redissonClient.getFairLock(lockKey);
        return fLock.tryLock();
    }

    public Boolean fairTryLock(String lockKey,long secondsTime){
        RLock fLock = redissonClient.getFairLock(lockKey);
        try {
            return fLock.tryLock(secondsTime,TimeUnit.SECONDS);
        }catch (Exception e){
            return false;
        }
    }

    public Boolean fairTryLock(String lockKey,long secondsTime,long timeout){
        RLock fLock = redissonClient.getFairLock(lockKey);
        try {
            return fLock.tryLock(timeout,secondsTime,TimeUnit.SECONDS);
        }catch (Exception e){
            return false;
        }
    }

    //================================ 读写锁 ==================================
    /**
     * 读锁
     */
    public Boolean readLock(String lockKey){
        RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
        rwLock.readLock().lock();
        return true;
    }

    public Boolean readLock(String lockKey, long secondsTime){
        RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
        rwLock.readLock().lock(secondsTime,TimeUnit.SECONDS);
        return true;
    }

    public Boolean tryReadLock(String lockKey){
        RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
        return rwLock.readLock().tryLock();
    }

    public Boolean tryReadLock(String lockKey, long secondsTime){
        RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
        try{
            return rwLock.readLock().tryLock(secondsTime,TimeUnit.SECONDS);
        }catch (Exception e){
            return false;
        }
    }

    public Boolean tryReadLock(String lockKey, long secondsTime, long timeout){
        RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
        try{
            return rwLock.readLock().tryLock(timeout,secondsTime,TimeUnit.SECONDS);
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 写锁
     */
    public Boolean writeLock(String lockKey){
        RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
        rwLock.writeLock().lock();
        return true;
    }

    public Boolean writeLock(String lockKey, long secondsTime){
        RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
        rwLock.writeLock().lock(secondsTime,TimeUnit.SECONDS);
        return true;
    }

    public Boolean writeTryLock(String lockKey){
        RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
        return rwLock.writeLock().tryLock();
    }

    public Boolean writeTryLock(String lockKey,long secondsTime){
        RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
        try {
            return rwLock.writeLock().tryLock(secondsTime,TimeUnit.SECONDS);
        }catch (Exception e){
            return false;
        }
    }

    public Boolean writeTryLock(String lockKey,long secondsTime,long timeout){
        RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
        try {
            return rwLock.writeLock().tryLock(timeout,secondsTime,TimeUnit.SECONDS);
        }catch (Exception e){
            return false;
        }
    }


}
