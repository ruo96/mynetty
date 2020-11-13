package com.wrh.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

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
}
