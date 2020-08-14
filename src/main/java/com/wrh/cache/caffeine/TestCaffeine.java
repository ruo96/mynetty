package com.wrh.cache.caffeine;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalListener;
import com.sun.beans.util.Cache;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.autoconfigure.cache.CacheProperties;

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
    public void Test2() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .recordStats()
                .build(k -> DataObject.get("Data for " + k));
        System.out.println(((DataObject)(cache.get("A"))).getData());
        System.out.println(((DataObject)(cache.get("B"))).getData());

    }

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
