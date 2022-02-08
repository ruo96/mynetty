package com.wrh.collection.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
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

    @Test
    public void Test35() {
        TreeMap<Integer, MyServer> treeMap = new TreeMap<>();
        treeMap.put(1, new MyServer("1"));
        treeMap.put(2, new MyServer("2"));
        treeMap.put(3, new MyServer("3"));
        treeMap.put(4, new MyServer("4"));
        treeMap.put(5, new MyServer("5"));
        treeMap.put(6, new MyServer("6"));

        Integer clientHashCode = 3;
        SortedMap<Integer, MyServer> tailMap = treeMap.tailMap(clientHashCode, false);
        System.out.println("tailMap = " + tailMap);

        MyServer server = tailMap.get(tailMap.firstKey());
        System.out.println("server = " + server);

    }
}
