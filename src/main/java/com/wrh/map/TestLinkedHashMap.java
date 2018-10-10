package com.wrh.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:28 2018/10/10 0010
 * @Modified By:
 */
public class TestLinkedHashMap {

    public static void main(String[] args) {
        LinkedHashMap<String, String> accessOrderedMap = new LinkedHashMap<String, String>(16,0.75F,true){
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

    }
}
