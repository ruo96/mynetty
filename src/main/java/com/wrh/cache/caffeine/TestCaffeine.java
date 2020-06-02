package com.wrh.cache.caffeine;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
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
}
