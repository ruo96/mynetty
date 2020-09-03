package com.wrh.collection.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
}
