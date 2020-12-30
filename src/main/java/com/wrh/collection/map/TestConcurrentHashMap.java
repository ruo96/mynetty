package com.wrh.collection.map;

import akka.stream.impl.SourceModuleIslandTag;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.security.action.GetLongAction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:27 2019/12/6 0006
 * @Modified By:
 */
@Slf4j
public class TestConcurrentHashMap {
    @Test
    public void test1(){
        Map<String,String> map  = new ConcurrentHashMap<>();
    }

    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;


    }
    @Test
    public void Test35() {
        System.out.println(tableSizeFor(5));
        System.out.println(tableSizeFor(15));
        System.out.println(tableSizeFor(25));

    }

    @Test
    public void Test43() throws InterruptedException {
        System.out.println(getLongAdd());

    }

    private Map<String, Long> getLongAdd() throws InterruptedException {
        int ITEM_COUNT = 4;
        int THREAD_COUNT = 10;
        int LOOP_COUNT = 100;
        ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool =  new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(()->{
            IntStream.rangeClosed(1,LOOP_COUNT).parallel().forEach(i->{
                String key = "---item"+ ThreadLocalRandom.current().nextInt(ITEM_COUNT);
                freqs.computeIfAbsent(key, k->new LongAdder()).increment();
            });
        });
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return freqs.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e->e.getValue().longValue()));
    }

    @Test
    public void Test76() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        System.out.println(map);
        map.putIfAbsent("w1",1);
        System.out.println(map);
        map.putIfAbsent("w1",2);
        System.out.println(map);
    }
    @Test
    public void Test85() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        System.out.println(map);
        map.computeIfAbsent("w1", k->1);
        System.out.println(map);
        map.computeIfAbsent("w1",k->2);
        System.out.println(map);

    }


}
