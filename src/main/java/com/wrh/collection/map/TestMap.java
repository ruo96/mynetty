package com.wrh.collection.map;

import com.alibaba.fastjson.JSON;
import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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

    @Test
    public void Test() {
        Map<String , String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        map.put("4","4");
        map.put("5","5");
        System.out.println(map);
        chechAbnormalValue(map);
        System.out.println(map);
        System.out.println(JSON.toJSONString(map));

    }

    /**
     * map的key排序
     */
    @Test
    public void TestSORT() {
        Map<String , String[]> map = new HashMap<>();
        /*map.put("1",{"1"});
        map.put("3",{"2"});
        map.put("21","3");
        map.put("22","4");
        map.put("221","5");
        System.out.println(map);
        List<String> list = sortMapByKey(map);
        for (String key:list  ) {
            System.out.println(map.get(key));
        }*/

    }

    private List<String> sortMapByKey(Map<String, String[]> map) {
        int size = map.size();

        List<Map.Entry<String, String[]>> list = new ArrayList<Map.Entry<String, String[]>>(size);
        list.addAll(map.entrySet());
        List<String> keys = list.stream()
                .sorted(Comparator.comparing(Map.Entry<String,String[]> :: getKey))
                .map(Map.Entry<String,String[]> :: getKey)
                .collect(Collectors.toList());
        return keys;

    }


    private void chechAbnormalValue(Map<String, String> map) {
        map.put("7","7");
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

        /*最高效率*/
        Set<Map.Entry<Integer,Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<Integer,Integer>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer,Integer> entry = iterator.next();
            log.info("===> set key: {}  value: {}",entry.getKey(),entry.getValue());
        }


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
        // 3. 使用Iterator
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

    @Test
    public void test4(){

        Map<Integer, Integer> productIdMap = new HashMap<>();

        productIdMap.put(null,1);

        Map<Integer, Integer> productIdMapNew = new HashMap<>(productIdMap);

        productIdMap.put(null,2);

        log.info("===> productIdMap is : {}", JSON.toJSONString(productIdMap));
        log.info("===> productIdMap value is : {}", productIdMap.get(null));

        log.info("===> productIdMapNew is : {}", JSON.toJSONString(productIdMapNew));
        log.info("===> productIdMapNew  value is : {}", productIdMapNew.get(null));

        if(productIdMap.containsKey(null)){
            System.out.println("with null key");
        }

        /**
         * 随便不允许key为null，但是编译期是无法检查出来的
         */
        Map<Integer, Integer> table1 = new Hashtable<>();
        table1.put(null,1);
        log.info("===> table1 is : {}", JSON.toJSONString(table1));

    }

    @Test
    public void test5() throws InterruptedException {
        HashMapThread thread1 = new HashMapThread();
        HashMapThread thread2 = new HashMapThread();
        HashMapThread thread3 = new HashMapThread();
        HashMapThread thread4 = new HashMapThread();
        HashMapThread thread5 = new HashMapThread();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
    }

    @Test
    public void test6(){
        Map<String,Set<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.add("5");

        map.put("1",set);
        log.info("===> map: {}",JSON.toJSONString(map));
        log.info("===> set: {}",JSON.toJSONString(set));

        set = null;
        log.info("===> map: {}",JSON.toJSONString(map));
        log.info("===> set: {}",JSON.toJSONString(set));
    }

    @Test
    public void Test7() {
        Map<String,String> map = new HashMap<>(16);
        log.info(">>> {}",map.size());

        String nullStr = map.get("abc");
        log.info(">>>{}",nullStr);
    }

    public Map<Integer, String> getMap(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1,"w1");
        map.put(2,"w2");
        map.put(3,"w3");
        map.put(4,"w4");
        return map;
    }

    public Map<Integer, Integer> getIntegerMap(){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);
        return map;
    }

    public Map<Integer, Student> getStudentMap(){
        Map<Integer, Student> map = new HashMap<>();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setId(1);
        s1.setGrade(1);
        map.put(1,s1);

        Student s2 = new Student();
        s2.setName("w2");
        s2.setId(2);
        s2.setGrade(2);
        map.put(2,s2);

        Student s3 = new Student();
        s3.setName("w3");
        s3.setId(3);
        s3.setGrade(3);
        map.put(3,s3);

        return map;
    }

    @Test
    public void Test316() {
        Map<Integer, String> map = getMap();
        String a = map.getOrDefault(5,"default");
        System.out.println(a);

    }

    @Test
    public void Test333() {
        Map<Integer, Student> map = getStudentMap();
        System.out.println("before: " + map);
        map.forEach((k,v)->{
            v.setName("w5");
        });

        System.out.println("after: " +map);
    }

    @Test
    public void Test368() {
        Map<Integer, Integer> map = getIntegerMap();
        String redisStr = JSON.toJSONString(map);

//        Map<Integer, Integer> map1 = JSON.parseObject(redisStr, Map<Integer, Integer>);

    }

    @Test
    public void Test386() {
        Map<String, String> map = new HashMap<>();
        System.out.println(map.size());

        map.putIfAbsent("key", "w1");
        System.out.println(map);

    }

    @Test
    public void Test393() {
        Map<String, Integer> map = new ConcurrentHashMap();
        map.put("key",1);
        while(true){
            int ori = map.get("key");
            int tar = ori + 1;
            boolean isSuccess = map.replace("key", ori, tar);
            if(isSuccess){
                break;
            }
        }
        System.out.println("end");

    }
}
