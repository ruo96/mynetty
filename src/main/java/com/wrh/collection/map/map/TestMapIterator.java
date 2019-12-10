package com.wrh.collection.map.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:38 2019/9/30 0030
 * @Modified By:
 */
@Slf4j
public class TestMapIterator {
    @Test
    public void test(){
        Map<Integer,Integer> map = new HashMap();
        map.put(1,11);
        map.put(2,21);
        map.put(3,31);
        map.put(4,41);
        map.put(5,61);
        map.forEach((k,v)->{
            log.info("key: {} value: {}",k.intValue(),v.intValue());
        });

    }
}
