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
}
