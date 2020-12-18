package com.wrh.cache.caffeine;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.*;
import com.sun.beans.util.Cache;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.concurrent.TimeUnit;

/**
 * @author wuruohong
 * @Classname TestCaffeine
 * @Description TODO
 * @Date 2020/6/1 18:20
 */
@Slf4j
public class TestCaffeine {

    @Test
    public void Test1() {
        Cache<String, Object> caffeineCache = (Cache<String, Object>) Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES).maximumSize(100).build();
    }

    @Test
    public void Test2() throws InterruptedException {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                //最大个数限制
                .maximumSize(256L)
                //初始化容量
                .initialCapacity(1)
                //访问后过期（包括读和写）
                .expireAfterAccess(2, TimeUnit.SECONDS)
                //写后过期
                .expireAfterWrite(2, TimeUnit.HOURS)
                //写后自动异步刷新
                .refreshAfterWrite(1, TimeUnit.HOURS)
                //记录下缓存的一些统计数据，例如命中率等
                .recordStats()
                //cache对缓存写的通知回调
                .writer(new CacheWriter<Object, Object>() {
                    @Override
                    public void write(@NonNull Object key, @NonNull Object value) {
                        log.info("key={}, CacheWriter write", key);
                    }

                    @Override
                    public void delete(@NonNull Object key, @Nullable Object value, @NonNull RemovalCause cause) {
                        log.info("key={}, cause={}, CacheWriter delete", key, cause);
                    }
                })
                //使用CacheLoader创建一个LoadingCache
                .build(new CacheLoader<String, String>() {
                    //同步加载数据
                    @Nullable
                    @Override
                    public String load(@NonNull String key) throws Exception {
                        return "value_" + key;
                    }

                    //异步加载数据
                    @Nullable
                    @Override
                    public String reload(@NonNull String key, @NonNull String oldValue) throws Exception {
                        return "value_" + key;
                    }
                });
        cache.put("w1","data1");
        System.out.println(((String)(cache.get("w1"))));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(((String)(cache.get("w1"))));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(((String)(cache.get("w1"))));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(((String)(cache.get("w1"))));
        System.out.println(cache.stats());
        System.out.println(cache.asMap());
        System.out.println(cache.policy().toString());

    }

    /**
     * Caffeien 是一个优秀的本地缓存，通过使用 W-TinyLFU 算法，
     * 高性能的 readBuffer 和 WriteBuffer，时间轮算法等，使得它拥有高性能，高命中率（near optimal），低内存占用等特点。
     */
    @Test
    public void Test3() {
        com.github.benmanes.caffeine.cache.Cache<String, String> cache =
                Caffeine.newBuilder()
                        // 数量上限
                        .maximumSize(1024)
                        .expireAfterWrite(5,TimeUnit.MINUTES)
                        .weakKeys()
                        .weakValues()
                        .removalListener((RemovalListener<String, String>)
                                (key,value,cause) -> System.out.println("key：" + key +",value:" + value + ",删除原因:"+ cause.toString())).build();

        cache.put("username", "wuruohong");
        cache.put("password", "123456");


        //从本地缓存中获取数据
        System.out.println(cache.getIfPresent("username"));
        System.out.println(cache.getIfPresent("password"));
        System.out.println(cache.getIfPresent("password123"));
        System.out.println(cache.get("blog",key->{
            // 本地缓存如果没有的话， 从数据库或者redis中获取
            return getValue(key);
        }));
    }

    private String getValue(String key) {
        return "default";
    }

    @Test
    public void Test() {
        String a = "a123c";
        String[] b = a.split("123");
        System.out.println(b[0]);
        System.out.println(b[1]);
    }
}
