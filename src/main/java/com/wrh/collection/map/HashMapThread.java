package com.wrh.collection.map;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:16 2019/11/15 0015
 * @Modified By:
 */
@Slf4j
public class HashMapThread extends Thread {

    private volatile AtomicInteger ai = new AtomicInteger();
    private static Map<Integer, Integer> map = new HashMap<>();

    @Override
    public void run() {
        while(ai.get() < 1000000){
            synchronized (HashMapThread.class){

                map.put(ai.get(),ai.get());
            }
            ai.incrementAndGet();
        }
        log.info("thread{} over! mapsize:{}  ai:{}", Thread.currentThread().getName(), map.size(), ai);

        List<String> list = new ArrayList<>();
    }
}
