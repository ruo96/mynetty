package com.wrh.cache;

import com.google.common.cache.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:32 2018/10/10 0010
 * @Modified By:
 */
public class TestCache {
    public static void main(String[] args) {
        Byte a = 12;
        Byte b = 12;
        System.out.println("a==b?"+ (a==b));

        Integer c = 11;

    }

    /**
     * 闪电缓存
     * @throws ExecutionException
     */
    @Test
    public void Test20() throws ExecutionException {
        LoadingCache<String, String> lc = CacheBuilder
                .newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        TimeUnit.SECONDS.sleep(2);
                        return "w1";
                    }
                });

        for (int i = 0; i < 10; i++) {
            System.out.println(System.currentTimeMillis());
            System.out.println(lc.get("w1"));
        }
    }

    @Test
    public void Test51() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)//写入多久没更新自动过期，先删除，后load
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "===" + notification.getKey());
                    }
                })
                .initialCapacity(20) //初始化容量
                .concurrencyLevel(10) // 并发
                .maximumSize(100) //最多缓存数量
                .recordStats() // 开启统计
                .build();

        cache.put("w1","r1");
        String w1 = cache.getIfPresent("w1");
        System.out.println("w1 = " + w1);

        String w2 = cache.getIfPresent("w2");
        System.out.println("w2 = " + w2);

        TimeUnit.SECONDS.sleep(4);
        w1 = cache.getIfPresent("w1");
        System.out.println("after 4 seconds w1 = " + w1);

    }
}
