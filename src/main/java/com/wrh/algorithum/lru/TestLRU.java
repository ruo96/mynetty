package com.wrh.algorithum.lru;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * @author wuruohong
 * @Classname TestLRU
 * @Description TODO
 * @Date 2020/12/28 19:12
 */
@Slf4j
public class TestLRU {
    @Test
    public void Test14() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("w1","v1");

        map.get("w1");

    }
}
