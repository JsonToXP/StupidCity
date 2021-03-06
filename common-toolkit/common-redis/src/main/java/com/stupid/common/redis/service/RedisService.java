package com.stupid.common.redis.service;

import com.stupid.common.core.toolkit.DataToolkit;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    //================= String ======================

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
     * All delete
     */
    public boolean delete(String key){
        redisTemplate.delete(key);
        return true;
    }

    //================= Hash ======================

    /**
     * hash
     * hset
     **/
    public boolean hset(String key,String hk,Object hv){
        redisTemplate.opsForHash().put(key,hk,hv);
        return true;
    }

    /**
     * hash
     * hset all
     **/
    public boolean hsetAll(String key,Object o){
        Map map = DataToolkit.object2Map(o);
        redisTemplate.opsForHash().putAll(key,map);
        return true;
    }

    /**
     * hash
     * put if absent ? false : true
     */
    public boolean hsetIfAbsent(String key,String hk,String hv){
        return redisTemplate.opsForHash().putIfAbsent(key,hk,hv);
    }


    /**
     * hash
     * hget
     */
    public Object hget(String key,Object hk){
        Object o = redisTemplate.opsForHash().get(key, hk);
        return o;
    }

    /**
     * hash
     * hget all
     */
    public <T> T hget(String key,Class<T> clazz){
        Map map = redisTemplate.opsForHash().entries(key);
        T t = DataToolkit.map2Object(map, clazz);
        return t;
    }

    /**
     * hash
     * delete
     */
    public boolean hdelete(String key,String... hk){
        redisTemplate.opsForHash().delete(key,hk);
        return true;
    }

    /**
     * hash
     * isEmpty
     */
    public boolean isEmpty(String key,String hk){
        return redisTemplate.opsForHash().hasKey(key,hk);
    }

    //================= List ======================

    /**
     * lpush + lpop ???????????? --- ???????????? ?????????
     * lpush + rpop ???????????? --- ???????????? ????????????
     *
     * lpush + ltrim ????????????
     * lpush + rpopAndLpush ????????????
     */

    /**
     * List
     * push left
     */
    public boolean lpush(String key,Object o){
        redisTemplate.opsForList().leftPush(key,o);
        return true;
    }

    /**
     * List
     * push left all
     */
    public boolean lpushAll(String key,List<Object> list){
        redisTemplate.opsForList().leftPushAll(key,list);
        return true;
    }

    /**
     * List
     * push left
     * list?????????push
     */
    public boolean lpushIfPresent(String key,Object o){
        redisTemplate.opsForList().leftPushIfPresent(key,o);
        return true;
    }

    /**
     * List
     * push right
     */
    public boolean rpush(String key,Object o){
        redisTemplate.opsForList().rightPush(key,o);
        return true;
    }

    /**
     * List
     * push right all
     */
    public boolean rpushAll(String key,List<Object> list){
        redisTemplate.opsForList().rightPushAll(key,list);
        return true;
    }

    /**
     * List
     * push right
     * list?????????push
     */
    public boolean rpushIfPresent(String key,Object o){
        redisTemplate.opsForList().rightPushIfPresent(key,o);
        return true;
    }

    /**
     * List
     * push
     * ????????????????????????????????????
     */
    public boolean pushIndex(String key, long index, Object o){
        redisTemplate.opsForList().set(key,index,o);
        return true;
    }

    /**
     * List
     * pop left
     * ??????????????????????????????
     */
    public <T> T lpop(String key,Class<T> clazz){
        Object o = redisTemplate.opsForList().leftPop(key);
        return DataToolkit.object2Class(o,clazz);
    }

    /**
     * List
     * pop right
     * ?????????????????????????????????
     */
    public <T> T rpop(String key,Class<T> clazz){
        Object o = redisTemplate.opsForList().rightPop(key);
        return DataToolkit.object2Class(o,clazz);
    }

    /**
     * List
     * index
     * ????????????????????????
     */
    public <T> T index(String key,long index,Class<T> clazz){
        Object o = redisTemplate.opsForList().index(key,index);
        return DataToolkit.object2Class(o,clazz);
    }

    /**
     * List
     * ?????? ??? start ??? end ??????????????????
     * start = 0 && end = -1 ????????????
     */
    public List get(String key,long start,long end){
        List list = redisTemplate.opsForList().range(key, start, end);
        return list;
    }

    /**
     * List
     * size
     */
    public long size(String key){
        return redisTemplate.opsForList().size(key);
    }

    /**
     * List
     * remove ??????
     * ????????????value?????????
     *  - flag = 0 ????????????
     *  - flag > 0 ???????????????????????????
     *  - flag < 0 ???????????????????????????
     */
    public boolean remove(String key,long flag,Object value){
        redisTemplate.opsForList().remove(key,flag,value);
        return true;
    }

    /**
     * List
     * trim ???List??????
     */
    public boolean trim(String key,long start, long end){
        redisTemplate.opsForList().trim(key,start,end);
        return true;
    }

    /**
     * List
     * ????????????????????????????????????????????????????????????????????????????????????
     */
    public <T> T rpopAndLpush(String fromKey,String toKey,Class<T> clazz){
        Object pop = redisTemplate.opsForList().rightPopAndLeftPush(fromKey, toKey);
        return DataToolkit.object2Class(pop,clazz);
    }

    //=========================================================

    /**
     * ??????lua??????
     */
    public Object execute(DefaultRedisScript script, List keys,Object...args){
        Object o = redisTemplate.execute(script, keys, args);
        return o;
    }

    //======================= ???????????? ==============================

    /**
     * ??????
     * ????????????????????????
     */
    public Boolean lock(String lockKey){
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return true;
    }

    /**
     * ?????? ??????????????????
     * ????????????????????????
     */
    public Boolean lock(String lockKey,long secondsTime){
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(secondsTime,TimeUnit.SECONDS);
        return true;
    }

    /**
     * ??????
     * ??????????????????false
     */
    public Boolean tryLock(String lockKey){
        RLock lock = redissonClient.getLock(lockKey);
        return lock.tryLock();
    }

    /**
     * ?????? ??????????????????
     * ??????????????????false
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
     * ?????? ?????????????????? ??????????????????
     * ???????????????????????????false
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
     * ?????????
     */
    public Boolean unlock(String lockKey){
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
        return true;
    }

    //================================ ????????? ==================================
    /**
     * ?????????
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

    //================================ ????????? ==================================
    /**
     * ??????
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
     * ??????
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
