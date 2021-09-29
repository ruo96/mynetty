package com.wrh.collection.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author wuruohong
 * @Classname TestLinkedHashMap
 * @Description TODO
 * @Date 2021/6/2 17:31
 */
@Slf4j
public class TestTreeMap {
    @Test
    public void Test14() {
        TreeMap<String,Integer> map = new TreeMap<String,Integer>(new MyTreeMapComparator());
        map.put("key_1", 1);
        map.put("key_2", 2);
        map.put("key_3", 3);
        Set<String> keys = map.keySet();
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext())
        {
            String key = iter.next();
            System.out.println(" "+key+":"+map.get(key));
        }

    }
}
