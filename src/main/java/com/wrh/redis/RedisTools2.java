package com.wrh.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class RedisTools2 {
    @Value("${redis.prefix}")
    private  String keyPrefix;
    @Resource(name = "strRedisTemplate")
    private RedisTemplate<String,String> strRedisTemplate;


    /**
     * 重新设置缓存失效时间
     * @param key 缓存的key
     * @param interval 失效时间
     * @param unit 时间单位
     * @return 是否设置成功
     */
    public Boolean expire(String key, Integer interval, TimeUnit unit){
        return strRedisTemplate.expire(keyPrefix + key, interval, unit);
    }

    /**
     * 重新设置缓存失效时间
     * @param key 缓存的key
     * @param time 失效的时刻
     * @return 是否设置成功
     */
    public Boolean expireAt(String key, Date time){
        return strRedisTemplate.expireAt(keyPrefix + key, time);
    }

    /**
     * 获取缓存剩余时间
     * @param key 缓存的key
     * @param unit 时间单位
     * @return 缓存剩余时间
     */
    public long getExpire(String key, TimeUnit unit){
        return strRedisTemplate.getExpire(keyPrefix + key, unit);
    }

    /**
     * 获取缓存剩余时间
     * @param key 缓存的key
     * @return 缓存剩余时间（单位毫秒）
     */
    public long getExpire(String key){
        return strRedisTemplate.getExpire(keyPrefix + key, TimeUnit.MILLISECONDS);
    }
    
    
    /**
     * 查询hashmap中的值
     * @param key 缓存的key
     * @param hKey hashmap数据的key
     * @return hashmap数据的value，如果不存在返回null
     */
    public Object hget(String key, String hKey){
        return strRedisTemplate.boundHashOps(keyPrefix + key).get(hKey);
    }

    /**
     * 存储或更新缓存
     * @param key 缓存的key
     * @param value 缓存值
     * @param interval 失效时间
     * @param unit 时间单位
     */
    public void set(String key, String value, Integer interval, TimeUnit unit){
        strRedisTemplate.opsForValue().set(keyPrefix + key, value, interval, unit);
    }
    
    /**
     * 存储或更新缓存
     * @param key
     * @param value
     * @param offset
     */
    public void set(String key, String value, long offset){
        strRedisTemplate.opsForValue().set(key, value, offset);
    }

    /**
     * 存储或更新缓存
     * @param key 缓存的key
     * @param value 缓存值
     */
    public void set(String key, String value){
        strRedisTemplate.opsForValue().set(keyPrefix + key, value);
    }

    /**
     * 获取缓存值
     * @param key 缓存的key
     * @return 缓存值
     */
    public Object get(String key){
        return strRedisTemplate.boundValueOps(keyPrefix + key).get();
    }

    /**
     * 删除缓存
     * @param key 缓存的key
     */
    public void delete(String key){
        strRedisTemplate.delete(keyPrefix + key);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        key = keyPrefix + key;
        return strRedisTemplate.hasKey(key);
    }


    public void decrement(String key) {
        strRedisTemplate.execute(new RedisCallback() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                Long incr = connection.decr((keyPrefix + key).getBytes());
                return incr;
            }
        });
    }



    /**
     * 不加前缀获取value值
     * @param key
     * @return
     */
    public Object getValueByKey(String key){
        return strRedisTemplate.opsForValue().get(key);
    }

    /**
     * 不加前缀赋value值
     * @param key
     * @return
     */
    public void setValueByKey(String key, String value){
        strRedisTemplate.opsForValue().set(key , value);
    }

    public void pipelineBatchInsert(List<Map<String, String>> saveList, TimeUnit unit, int timeout) {
        /* 插入多条数据 */
        strRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                for (Map<String, String> needSave : saveList) {
                    strRedisTemplate.opsForValue().set(needSave.get("key"), needSave.get("value"), timeout,unit);
                }
                return null;
            }
        });
    }

    public void pipelineMultiOpr(Map<String, List<Map<String, String>>> oprMap, TimeUnit unit, int timeout) {
        /* 插入多条数据 */
        strRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                Iterator<Map.Entry<String, List<Map<String, String>>>> iterator = oprMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, List<Map<String, String>>> entry = iterator.next();
                    String oprType = entry.getKey();
                    switch (oprType) {
                        case "INSERT":
                            batchInsert(entry.getValue(), TimeUnit.SECONDS, timeout);
                            break;
                        case "INCR":
                            batchIncr(entry.getValue(),TimeUnit.SECONDS,timeout);
                            break;
                        default:
                            break;
                    }
                }
                return null;
            }
        });
    }

    private void batchInsert(List<Map<String, String>> saveList, TimeUnit unit, int timeout) {
        for (Map<String, String> needSave : saveList) {
            strRedisTemplate.opsForValue().set(needSave.get("key"), needSave.get("value"), timeout,unit);
        }
    }

    private void batchIncr(List<Map<String, String>> oprList, TimeUnit unit, int timeout) {
        for (Map<String, String> needOpr : oprList) {
            strRedisTemplate.opsForValue().increment(needOpr.get("key"), 1L);
            strRedisTemplate.expire(needOpr.get("key"), timeout,unit);
        }
    }
}
