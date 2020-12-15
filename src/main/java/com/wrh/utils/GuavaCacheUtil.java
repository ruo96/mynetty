package com.wrh.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author sunbo
 * @Description: Guava缓存
 * @CreateDate: Created in 2018/5/24 16:47
 */
public class GuavaCacheUtil {

    /** 字符串为null或[] */
    public static final String NULL_STR = "null";
    public static final String BRACKETS_STR = "[]";

    private static final Logger LOGGER = LoggerFactory.getLogger(GuavaCacheUtil.class);

    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder()
            .initialCapacity(1000)
            .maximumSize(2000)
            .expireAfterWrite(6L, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return "null";
                }
            });


    public static void putValue(String key, String value) {
        localCache.put(key, value);
    }

    public static String getValue(String key) {
        String value = "";
        try {
            value = localCache.get(key);
            if (NULL_STR.equals(value) || BRACKETS_STR.equals(value)) {
                return StringUtils.EMPTY;
            }
        } catch (ExecutionException e) {
            LOGGER.error("获取本地Guava缓存出错：", e.getMessage());
        }

        return value;
    }

    public static void refreshCache(String key) {
        localCache.refresh(key);
    }

    public static void deleteCache(String key) {
        localCache.invalidate(key);
    }

    public static String stats() {
        return localCache.stats().toString();
    }
}
