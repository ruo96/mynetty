package com.wrh.collection.map;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:14 2018/9/17 0017
 * @Modified By:
 */
@Slf4j
public class TestMap {

    public static String getAccountIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        String prefix = "user";
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        return prefix + String.format("%015d", hashCodeV);
    }



    public static void main(String[] args) {
        /*Map<String, Object> map = new HashMap<>();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        map.put("4","d");

        System.out.println(map.keySet());
        System.out.println(map.values());
        System.out.println(map.entrySet());

        Integer mm = 111;
        Field field = null;
//        try{
            field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            field.TestSet(mm,101);
            Integer nn = 128;
            System.out.println(mm);
            System.out.println(nn);
        }catch (Exception e){
            e.printStackTrace();
        }*/


        /*System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 5; i++) {
            String s = UUID.randomUUID().toString();
//            s = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
            System.out.println(s.substring(0, 12));
            System.out.println(s);
        }
*/
        System.out.println(TestMap.getAccountIdByUUId());

        try {
            Thread.sleep(1000L);
            System.out.println("sleep done");
            return;
        } catch (InterruptedException e) {
            System.out.println("catch exception");
        }finally {
            System.out.println("finally");
        }


    }

    @Test
    public void test(){
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        map.put(1,list);

        log.info("===> map is : {}", JSON.toJSONString(list));

        map.get(1).add(5);
        log.info("===> map is : {}", JSON.toJSONString(list));

    }

    @Test
    public void test1(){

        Map<Integer, Map<String, Integer>> productIdMap = new HashMap<>();

        Map<String, Integer> map = new HashMap<>();
        map.put("1",1);

        productIdMap.put(1000, map);

        log.info("===> productIdMap is : {}", JSON.toJSONString(productIdMap));

        productIdMap.get(1000).put("2", 2);
        log.info("===> productIdMap is : {}", JSON.toJSONString(productIdMap));

    }

    @Test
    public void test2(){

        Map<Integer, Integer> productIdMap = new HashMap<>();

        productIdMap.put(1,1);

        Map<Integer, Integer> productIdMapNew = new HashMap<>(productIdMap);

        productIdMap.put(2,2);

        log.info("===> productIdMap is : {}", JSON.toJSONString(productIdMap));

        log.info("===> productIdMapNew is : {}", JSON.toJSONString(productIdMapNew));

    }

    /**
     * 遍历map的方式
     */
    @Test
    public void test3(){

        Map<Integer, Integer> map = new HashMap<>();

        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);

        // 1. entrySet遍历，在键和值都需要时使用（最常用）
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            log.info("===> key: {}   value: {}" ,entry.getKey(), entry.getValue());
        }

        // 2. 通过keySet或values来实现遍历,性能略低于第一种方式
        // 遍历map中的键
        for (Integer key : map.keySet()) {
            System.out.println("key = " + key);
        }
        // 遍历map中的值
        for (Integer value : map.values()) {
            System.out.println("key = " + value);
        }
        // 3. 使用Iterator遍历
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }

        // 4. java8 Lambda
        // java8提供了Lambda表达式支持，语法看起来更简洁，可以同时拿到key和value，
        // 不过，经测试，性能低于entrySet,所以更推荐用entrySet的方式
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });




    }
}
