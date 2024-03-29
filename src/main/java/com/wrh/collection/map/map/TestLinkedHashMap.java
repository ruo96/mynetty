package com.wrh.collection.map.map;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:28 2018/10/10 0010
 * @Modified By:
 */
@Slf4j
public class TestLinkedHashMap {

    public static void main(String[] args) {
        /*LinkedHashMap<String, String> accessOrderedMap = new LinkedHashMap<String, String>(16,0.75F,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String,String> eldest){
                return size()>3;
            }
        };

        accessOrderedMap.put("Project1","Valhalla");
        accessOrderedMap.put("Project2","Panama");
        accessOrderedMap.put("Project3","Loom");

        accessOrderedMap.forEach((k,v) -> {
            System.out.println(k + ":" + v);
        });

        accessOrderedMap.get("Project1");
        accessOrderedMap.get("Project2");
        accessOrderedMap.get("Project3");

        System.out.println("Iterate over should be not affected:");
        accessOrderedMap.forEach((k,v) -> {
            System.out.println(k + ":" + v);
        });

        accessOrderedMap.put("Project4","Mission Control");
        System.out.println("Oldest should be removed");
        accessOrderedMap.forEach((k,v) -> {
            System.out.println(k + ":" + v);
        });

        List<String> list = new ArrayList<>();
        log.info("size is : {}", list.size());*/

        Map<String,String> map = new HashMap<>();
        map.put("123","1");
        map.put("456",null);

        System.out.println("size: " + map.size());
        System.out.println("map: " + map);

    }
}
