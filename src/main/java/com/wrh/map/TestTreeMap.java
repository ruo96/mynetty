package com.wrh.map;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Vector;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:03 2019/1/11 0011
 * @Modified By:
 */
public class TestTreeMap {

    public static void main(String[] args) {

        /*treemap是有自己的顺序的*/
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("3","value3");
        treeMap.put("13","value1");
        treeMap.put("2","value2");

        System.out.println(JSON.toJSONString(treeMap));

        /*linkedHashMap是根据插入的顺序来保存的*/
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("3","value3");
        linkedHashMap.put("4","value4");
        linkedHashMap.put("1","value1");
        linkedHashMap.put("2","value2");
        System.out.println(JSON.toJSONString(linkedHashMap));

        /*vector是有顺序的*/
        Vector<String> vector = new Vector<>();
        vector.add("4");
        vector.add("2");
        vector.add("3");
        vector.add("1");
        System.out.println(JSON.toJSONString(vector));
    }
}
