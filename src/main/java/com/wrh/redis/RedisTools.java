package com.wrh.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作组件类
 * @author dancy
 * @date 2015年4月01日
 * @version 1.0
 * @copyright ZCSmart Co.,Ltd.copyright 2015
 */
@Primary
@Component
public class RedisTools {
    @Value("${redis.prefix}")
    private  String keyPrefix;
    @Resource(name = "redisTemplate")
    private RedisTemplate<Object,Object> redisTemplate;


    /**
     * 重新设置缓存失效时间
     * @param key 缓存的key
     * @param interval 失效时间
     * @param unit 时间单位
     * @return 是否设置成功
     */
    public Boolean expire(String key, Integer interval, TimeUnit unit){
        return redisTemplate.expire(keyPrefix + key, interval, unit);
    }

    /**
     * 重新设置缓存失效时间
     * @param key 缓存的key
     * @param time 失效的时刻
     * @return 是否设置成功
     */
    public Boolean expireAt(String key, Date time){
        return redisTemplate.expireAt(keyPrefix + key, time);
    }

    /**
     * 获取缓存剩余时间
     * @param key 缓存的key
     * @param unit 时间单位
     * @return 缓存剩余时间
     */
    public long getExpire(String key, TimeUnit unit){
        return redisTemplate.getExpire(keyPrefix + key, unit);
    }

    /**
     * 获取缓存剩余时间
     * @param key 缓存的key
     * @return 缓存剩余时间（单位毫秒）
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(keyPrefix + key, TimeUnit.MILLISECONDS);
    }
    
    
    /**
     * 查询hashmap中的值
     * @param key 缓存的key
     * @param hKey hashmap数据的key
     * @return hashmap数据的value，如果不存在返回null
     */
    public Object hget(String key, String hKey){
        return redisTemplate.boundHashOps(keyPrefix + key).get(hKey);
    }

    /**
     * 存储或更新缓存
     * @param key 缓存的key
     * @param value 缓存值
     * @param interval 失效时间
     * @param unit 时间单位
     */
    public void set(String key, Object value, Integer interval, TimeUnit unit){
        redisTemplate.opsForValue().set(keyPrefix + key, value, interval, unit);
    }
    
    /**
     * 存储或更新缓存
     * @param key
     * @param value
     * @param offset
     */
    public void set(String key, Object value, long offset){
        redisTemplate.opsForValue().set(key, value, offset);
    }

    /**
     * 存储或更新缓存
     * @param key 缓存的key
     * @param value 缓存值
     */
    public void set(String key, Object value){
        redisTemplate.opsForValue().set(keyPrefix + key, value);
    }

    /**
     * 获取缓存值
     * @param key 缓存的key
     * @return 缓存值
     */
    public Object get(String key){
        return redisTemplate.boundValueOps(keyPrefix + key).get();
    }

    /**
     * 删除缓存
     * @param key 缓存的key
     */
    public void delete(String key){
        redisTemplate.delete(keyPrefix + key);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        key = keyPrefix + key;
        return redisTemplate.hasKey(key);
    }

    /**
     * 自增
     * @param key
     * @return
     */
    public long increment(String key){
        return increment(key, 1L, 0L);
    }

    public void decrement(String key) {
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                Long incr = connection.decr((keyPrefix + key).getBytes());
                return incr;
            }
        });
    }

    /**
     * 自增
     * @param key
     * @param delta
     * @return
     */
    public long increment(String key, long delta, long initialValue){
        String prefix= keyPrefix + key;
        if(!redisTemplate.hasKey(prefix)){
            redisTemplate.opsForValue().setIfAbsent(prefix, initialValue);
        }
        return redisTemplate.opsForValue().increment(prefix, delta);
    }

    /**
     * 不加前缀获取value值
     * @param key
     * @return
     */
    public Object getValueByKey(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 不加前缀赋value值
     * @param key
     * @return
     */
    public void setValueByKey(String key, int value){
         redisTemplate.opsForValue().set(key , value);
    }
}
