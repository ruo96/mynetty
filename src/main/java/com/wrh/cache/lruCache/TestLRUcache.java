package com.wrh.cache.lruCache;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:25 2019/12/5 0005
 * @Modified By:
 */
@Slf4j
public class TestLRUcache {
    @Test
    public void test1(){
        LRUcache<Integer, String> cache = new LRUcache<>();
        cache.put(1,"a");
        cache.put(2,"b");
        cache.put(3,"c");
        log.info("===>开始时候：{}", cache.keySet());
        cache.get(1);
        log.info("===>get第一个之后：{}", cache.keySet());
        cache.put(4,"d");
        log.info("===>put一个新值之后：{}", cache.keySet());
    }

    @Test
    public void test2(){
        LinkedHashMap<Integer, String> hashMap = new LinkedHashMap<>();
        hashMap.put(1,"a");
        hashMap.put(2,"b");
        hashMap.put(3,"c");
        log.info("===>开始时候：{}", hashMap.keySet());
        hashMap.put(4,"c");
        log.info("===>开始时候：{}", hashMap.keySet());

    }
}
