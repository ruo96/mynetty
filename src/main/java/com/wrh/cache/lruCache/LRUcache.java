package com.wrh.cache.lruCache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:22 2019/12/5 0005
 * @Modified By:
 */
public class LRUcache<K,V> extends LinkedHashMap<K,V> {
    private static final int MAX_ENTRIES = 3;

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_ENTRIES;
    }

    public LRUcache() {
        super(MAX_ENTRIES, 0.75F,true);
    }
}
