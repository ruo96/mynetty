package com.wrh.collection.map.map;

import com.alibaba.fastjson.JSON;
import com.wrh.collection.map.map.vo.MapObj;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:25 2019/11/22 0022
 * @Modified By:
 */
@Slf4j
public class TestMapOpr {

    @Test
    public void test(){
        List<MapObj> list = new ArrayList<>();
        MapObj obj1 = new MapObj();
        obj1.setName("w1");
        obj1.setCar("bmw");
        obj1.setAge(18);

        MapObj obj2 = new MapObj();
        obj2.setName("w2");
        obj2.setCar("benz");
        obj2.setAge(20);

        list.add(obj1);
        list.add(obj2);

        log.info("===> list is : {}", JSON.toJSONString(list));

        Map<String, String> map = list.stream().collect(Collectors.toMap(MapObj::getName,MapObj::getCar));

        log.info("===> map is : {}", JSON.toJSONString(map));

    }

    @Test
    public void Test1() {
        Map<String, String> map = new HashMap<>();
        map.put(null,null);
        map.put("1","w1");
        map.put("2","w2");
        map.put("3","w3");
        map.put(null,null);

        System.out.println(map);
    }

    @Test
    public void Test2() {
        Map<String, String> map = new HashMap<>();
        map.put(null,null);
        map.put("1","w1");
        map.put("2","w2");
        map.put("3","w3");
        System.out.println(map.get("4"));
    }

    /**
     * compute：计算并更新值
     * computeIfAbsent：Value不存在时才计算
     * computeIfPresent：Value存在时才计算
     */
    @Test
    public void Test70() {
        /*List<String> animals = Arrays.asList("dog", "cat", "cat", "dog", "fish", "dog");
        Map<String, Integer> map = new HashMap<>();
        for(String animal : animals){
            Integer count = map.get(animal);
            map.put(animal, count == null ? 1 : ++count);
        }
        System.out.println(map);*/

        List<String> animals = Arrays.asList("dog", "cat", "cat", "dog", "fish", "dog");
        Map<String, Integer> map = new HashMap<>();
        for(String animal : animals){
            map.compute(animal, (k, v) -> v == null ? 1 : ++v);
        }
        System.out.println(map);
        
    }
}
