package com.wrh.collection.map;

import akka.stream.impl.SourceModuleIslandTag;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.security.action.GetLongAction;

import java.util.HashMap;
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
        System.out.println("1,n="+n);
        System.out.println("n>>>1 :"+(n>>>1));
        n |= n >>> 1;
        System.out.println("n |= n >>> 1,n="+n);
        n |= n >>> 2;
        System.out.println("n |= n >>> 2,n="+n);
        n |= n >>> 4;
        System.out.println("n |= n >>> 4,n="+n);
        n |= n >>> 8;
        System.out.println("n |= n >>> 8,n="+n);
        n |= n >>> 16;
        System.out.println("n |= n >>> 16,n="+n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Test
    public void Test55() {
        System.out.println("hash(1) = " + hash(1));
        System.out.println("hash(10) = " + hash(10));
        System.out.println("hash(100) = " + hash(100));
        System.out.println("hash(1000) = " + hash(1000));
        System.out.println("hash(10000) = " + hash(10000));
        System.out.println("hash(100000) = " + hash(100000));
        System.out.println("hash(1000000) = " + hash(1000000));

        System.out.println("hash(a) = " + hash("a"));
        System.out.println("hash(b) = " + hash("b"));
        System.out.println("hash(c) = " + hash("c"));

        System.out.println("hash(A) = " + hash("A"));
        System.out.println("hash(B) = " + hash("B"));
        System.out.println("hash(C) = " + hash("C"));
    }

    @Test
    public void Test49() {
        tableSizeFor(8);

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

    @Test
    public void Test133() {
        Map<Integer, Integer> map = new HashMap<>();


    }


}
