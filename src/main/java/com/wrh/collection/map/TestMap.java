package com.wrh.collection.map;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.wrh.collection.map.vo.GameDayDataV2;
import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:14 2018/9/17 0017
 * @Modified By:
 */
@Slf4j
public class TestMap {
    private static final Logger LOGGER= LoggerFactory.getLogger(TestMap.class);

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
     * 遍历map的方式   map遍历
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

        /** 这个性能应该也可以*/
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
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

    @Test
    public void Test413() {
        Map<Integer, Student> map = new HashMap<>();
        final long[] i = {0};
        map.forEach((k,v) ->{
            i[0] += v.getGrade();
        });
        System.out.println(i[0]);

    }

    @Test
    public void Test424() {
        Map<String, Map<String, List<Student>>> map = new HashMap<>();

        Map<String, List<Student>> mapInner = new HashMap<>();
        List<Student> l1 = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setId(1);
        s1.setGrade(1);

        Student s2 = new Student();
        s2.setName("w2");
        s2.setId(2);
        s2.setGrade(2);

        l1.add(s1);
        l1.add(s2);

        mapInner.put("01",l1);
        map.put("test", mapInner);
        log.info(">>> map:{}", JSON.toJSONString(map));


    }

    @Test
    public void Test450() {
        /** 还是要用concurrentHashMap*/
        Map<Integer, String> map = getMap();
        System.out.println(map);
        map.putIfAbsent(4,"w5");
        System.out.println( map.putIfAbsent(5,"w6"));
        System.out.println( map.putIfAbsent(5,"w5"));
        System.out.println( map);

    }

    @Test
    public void test$1() {

        Long m1 = Runtime.getRuntime().freeMemory();
        List<Map<String, Object>> resultList = new ArrayList<>();

        Map<String, List<GameRealTimeData>> dsDataListMap = new HashMap<>();
        dsDataListMap.put("2020-08-01", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-02", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-03", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-04", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-05", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-06", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-07", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-08", new ArrayList<>(1440));
        Long m2 = Runtime.getRuntime().freeMemory();
        System.out.println((m1-m2)/1024/1024);
        Long s1 = System.currentTimeMillis();
        dsDataListMap.forEach((ds, list) -> {
            //创建应该有的槽点数
            int[] yArray = StringUtils.equals(DateUtil.format(DateUtil.SIMPLE_DATE_FORMAT), ds)
                    ? new int[list.isEmpty()?0:(list.get(list.size()-1).getSlotNum()+1)] : new int[1440];
            Map<String, Object> temp = new HashMap<>(1);
            for (GameRealTimeData data : list) {
                Integer slotNum = data.getSlotNum();
                //将查询的数据插入对应的槽点
                if (slotNum <= (yArray.length - 1)) {
                    yArray[slotNum] = data.getY();
                }
            }
            temp.put("name", ds);
            temp.put("data", yArray[0]);
            resultList.add(temp);
//            yArray[0] = new int[0];
        });
        System.out.println("cost: " + (System.currentTimeMillis() - s1));
        Long m3  = Runtime.getRuntime().freeMemory();
        System.out.println((m1-m3)/1024);

        Long start = System.currentTimeMillis();
//        System.gc();
        System.out.println(System.currentTimeMillis() - start);
        Long m4  = Runtime.getRuntime().freeMemory();
        System.out.println((m1-m4)/1024/1024);
    }

    @Test
    public void test$2() {

        Long m1 = Runtime.getRuntime().freeMemory();
        List<Map<String, Object>> resultList = new ArrayList<>();

        Map<String, List<GameRealTimeData>> dsDataListMap = new HashMap<>();
        dsDataListMap.put("2020-08-01", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-02", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-03", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-04", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-05", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-06", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-07", new ArrayList<>(1440));
        dsDataListMap.put("2020-08-08", new ArrayList<>(1440));
        Long m2 = Runtime.getRuntime().freeMemory();
        System.out.println((m1-m2)/1024/1024);

        Set<Map.Entry<String, List<GameRealTimeData>>> entrySet = dsDataListMap.entrySet();
        Iterator<Map.Entry<String, List<GameRealTimeData>>> iterator = entrySet.iterator();
        Long s1 = System.currentTimeMillis();
        while (iterator.hasNext()){
            Map.Entry<String, List<GameRealTimeData>> entry = iterator.next();
//            log.info("===> set key: {}  value: {}",entry.getKey(),entry.getValue());
            String ds = entry.getKey();
            List<GameRealTimeData> list = entry.getValue();


                //创建应该有的槽点数
                int[] yArray = StringUtils.equals(DateUtil.format(DateUtil.SIMPLE_DATE_FORMAT), ds)
                        ? new int[list.isEmpty()?0:(list.get(list.size()-1).getSlotNum()+1)] : new int[10];
                Map<String, Object> temp = new HashMap<>(1);
                for (GameRealTimeData data : list) {
                    Integer slotNum = data.getSlotNum();
                    //将查询的数据插入对应的槽点
                    if (slotNum <= (yArray.length - 1)) {
                        yArray[slotNum] = data.getY();
                    }
                }
                temp.put("name", ds);
                temp.put("data", yArray);
                resultList.add(temp);
//            yArray[0] = new int[0];

        }
        System.out.println("cost: " + (System.currentTimeMillis() - s1));


        Long m3  = Runtime.getRuntime().freeMemory();
        System.out.println((m1-m3)/1024);

        Long start = System.currentTimeMillis();
//        System.gc();
        System.out.println(System.currentTimeMillis() - start);
        Long m4  = Runtime.getRuntime().freeMemory();
        System.out.println((m1-m4)/1024/1024);
    }

    @Test
    public void test$3() {
        for (int i = 0; i < 3; i++) {
            test$1();
        }

        System.out.println("=======================");

        for (int i = 0; i < 3; i++) {
            test$2();
        }
    }

    @Test
    public void test$4() {
        Map<String, String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","1");
        map.put("3","1");
        map.put("4","1");
        long m1 = Runtime.getRuntime().freeMemory()/1024;
        System.out.println("start memory: " + m1 + "kb");
        map.forEach((k,v)->{
            int[] i = new int[1024*1024];
            for (int j = 0; j < 1024*1024; j++) {
                i[j] = j;
            }
            long m2 = Runtime.getRuntime().freeMemory()/1024;
            System.out.println("this time memory: " + m2 + "kb");
            System.out.println(m1 - m2);
        });
        long m2 = Runtime.getRuntime().freeMemory()/1024;
        System.out.println("finally");
        System.out.println(m1 - m2);
    }

    @Test
    public void test$5() {
        Map<String, String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","1");
        map.put("3","1");
        map.put("4","1");
        long m1 = Runtime.getRuntime().freeMemory()/1024;
        System.out.println("start memory: " + m1 + "kb");
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        System.out.println(map.size());
        while (iterator.hasNext()){
                int[] i = new int[1024*1024];
                /*for (int j = 0; j < 1024*1024; j++) {
                    i[j] = j;
                }*/
                long m2 = Runtime.getRuntime().freeMemory()/1024;
            System.out.println("this time memory: " + m2 + "kb");
                System.out.println(">>> "+(m1 - m2) + "kb");
        }
        long m2 = Runtime.getRuntime().freeMemory()/1024;

        System.out.println("finally");
        System.out.println(m1 - m2);
    }

    /**
     * treemap排序 TreeMap 底层使用了数组+红黑树实现，所以里面的存储结构可以理解成下面这幅图
     */
    @Test
    public void Test630() {
        TreeMap<Integer, Object> naturalSort = new TreeMap<>();

        TreeMap<Integer, Object> customSort = new TreeMap<>((o1,o2)-> Integer.compare(o2,o1));
        customSort.put(2,1);
        customSort.put(1,1);
        customSort.put(9,1);
        System.out.println(customSort);

    }

    /**
     * map序列化与反序列化
     */
    @Test
    public void Test645() {


    }

    /**
     * map排序
     */
    @Test
    public void Test654() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(6,1);
        map.put(2,1);
        map.put(9,1);
        map.put(0,1);
        map.put(-1,1);

        Map<Integer, Integer> map1 = new TreeMap<>();
        map1.putAll(map);

        System.out.println(map);
        System.out.println(map1);

    }

    /**
     * 获取key的最小值
     */
    @Test
    public void Test674() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(6,1);
        map.put(2,1);
        map.put(9,1);
        map.put(0,1);
        map.put(-1,1);

        Object[] arrays = map.keySet().toArray();
        Arrays.sort(arrays);
        int firstRetention = (int) arrays[0];
        System.out.println(firstRetention);

    }

    @Test
    public void Test693() {
        float[] i = new float[4];
        System.out.println(i[0]);
        System.out.println(i[1]);
        System.out.println(i[2]);
        System.out.println(i[3]);

        if(0 == i[0]){
            System.out.println("equal");
        }else {
            System.out.println("not equal");
        }

    }

    /**
     * map根据key排序  根据value排序  map排序
     */
    @Test
    public void Test709() {
        Map<Integer, Long> map = new HashMap<>();
        map.put(6,90000000000L);
        map.put(2,588888888L);
        map.put(9,299999999L);
        map.put(0,123333333L);
        map.put(-1,355555555L);

        Map<Integer, Long> map2 = new LinkedHashMap<>();
        map2.put(6,9L);
        map2.put(2,5L);
        map2.put(9,2L);
        map2.put(0,1L);
        map2.put(-1,3L);

        Map<Integer, Long> map1 = new TreeMap<>();
        map1.putAll(map);

        System.out.println(map);
        System.out.println(map1);
        System.out.println(map2);

        Comparator<Map.Entry<Integer,Long>> valueComparator = new Comparator<Map.Entry<Integer, Long>>() {
            @Override
            public int compare(Map.Entry<Integer, Long> o1, Map.Entry<Integer, Long> o2) {
                return (o2.getValue() - o1.getValue()) > 0 ? 1:-1;
            }
        };

        List<Map.Entry<Integer,Long>> list = new ArrayList<Map.Entry<Integer, Long>>(map.entrySet());

        Collections.sort(list, valueComparator);

        list = list.stream().limit(3).collect(Collectors.toList());
        for(Map.Entry<Integer,Long> entry:list){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }

    /**
     * 叠加map中相同的key
     */
    @Test
    public void Test753() {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("w1", 1);
        map1.put("w2", 2);
        map1.put("w3", 3);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("w2", 2);
        map2.put("w3", 3);
        map2.put("w4", 4);

        System.out.println(map1);
        System.out.println(map2);

        Map<String, Integer> map3 = new HashMap<>();
        map3.putAll(map1);
        System.out.println(map3);

        System.out.println("======================");

        map2.forEach((key,value)->map3.merge(key,value,(v1,v2)->v1+v2));
        System.out.println(map3);
    }

    /**
     * 如果map中存在key就操作合并进去 map合并
     */
    @Test
    public void Test781() {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("w1", 1);
        map1.put("w2", 2);
        map1.put("w3", 3);
        System.out.println(map1);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("w2", 3);
        map2.put("w3", 4);
        map2.put("w4", 4);

        List<MapClass> list = new ArrayList<>();
        MapClass m1 = new MapClass("w3",4);
        MapClass m2 = new MapClass("w4",9);
        MapClass m3 = new MapClass("w3",10);
        list.add(m1);
        list.add(m2);
        list.add(m3);

        /*list.stream().forEach(e->{
            System.out.println(e.getName() +"："+e.getScore());
            map1.compute(e.getName(), (k, v) -> {
                if(v==null){
                    v = e.getScore();
                }else {
                    v += e.getScore();
                }
                return v;
            });
        });*/

        map2.forEach((k1,v1)->{
            map1.compute(k1, (k, v) -> {
                if(v==null){
                    v = v1;
                }else {
                    v += v1;
                }
                return v;
            });
        });

        System.out.println(map1);

    }

    public class MapClass{
        private String name;
        private Integer score;

        public MapClass(String name, Integer score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
    }

    @Test
    public void Test858() {
        Map<Integer, List<String>> map = new HashMap<>();
        System.out.println(map.size());

    }

    @Test
    public void Test865() {
        Map<Integer, List<GameDayDataV2>> map = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalData", map.getOrDefault("gameBaseId1", new ArrayList<>()).stream().mapToInt(GameDayDataV2::getNewAccCnt).sum());
        System.out.println(resultMap);


        List<GameDayDataV2> list = new ArrayList<>();
        System.out.println(list.stream().mapToInt(GameDayDataV2::getNewAccCnt).sum());


    }

    @Test
    public void Test880() {
        Map<String, Long> map;
        List<GameDayDataV2> list = new ArrayList<>();

        map = list.stream().filter(e->Objects.nonNull(e.getPaySumMoney())).collect(Collectors.toMap(GameDayDataV2::getDsStr, GameDayDataV2::getPaySumMoney));
        if(map.containsKey("w1")){
            System.out.println("map contain key w1");
        }else {
            System.out.println("map not contain key w1");
        }

    }

    @Test
    public void Test894() {
        String a = "w1";
        int hashcode = a.hashCode();
        LOGGER.info("hashcode: {}", hashcode);
        int hashValue = hashcode >>> 16;
        LOGGER.info("hashcode >>> 16: {}", hashValue);
        int result = hashcode ^ hashValue;
        LOGGER.info("hashcode ^ hashValue: {}", result);
        /**
         * hashmap的长度为什么都是2的次防，就是因为可以用其长度-1做低位掩码， 可以很快速的确定数据的位置
         */

        Map<String, Integer> map = new HashMap<>();
        map.put("w1",1);


    }

    @Test
    public void Test916() {
        Long a = 100L;
        Long b = 33L;
        Double c = (double) a / (double) b;
        System.out.println(c);

    }

    /**
     * map合并  多种方法
     */
    @Test
    public void Test925() {
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();

        map1.put("w1","r1");
        map1.put("w2","r2");
        map1.put("w3","r3");

        map2.put("w3","r3");
        map2.put("w4","r4");
        map2.put("w5","r5");

        /** 0 */
        Map<String,String> map0 = new HashMap<>(map1);
        map2.forEach((k,v)->map0.merge(k,v,(v1,v2)->v2));
        System.out.println("map0: " + map0);

        /** 1 */
        Map<String,String> map3 = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1,v2) -> v2));
        System.out.println(map3);

        /** 2 */
        Map<String, String> map4 = Stream.of(map1,map2)
                .flatMap(map-> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1,v2)->v2));
        System.out.println(map4);

        /** 合并并且反转*/
        Map<String, String> map5 = Stream.of(map1,map2)
                .flatMap(map-> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey, (v1,v2)->v2));
        System.out.println("fanzhuan: "+map5);

        /** 增强库  需要找下再哪个jar包里面*/
//        Map<String, String> map6 = EntryStream.of(map1)
//                .append(EntryStream.of(map2))
//                .toMap((e1, e2) -> e1);

    }

    @Test
    public void Test971() {
        Map<String, String> map = new HashMap<>();
        map.put("null", "1");
        System.out.println(map);

    }

    /**
     * 对象转map
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    @Test
    public void Test979() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Student s = new Student();
        s.setName("w1");
        s.setId(1);
        s.setGrade(2);
//        s.setMoney(3L);
        s.setTitle("t1");
        s.setFlag(true);

        Map<String, Object> map = BeanUtils.describe(s);
        map.remove("class");
        System.out.println("map = " + map);


    }

    @Test
    public void Test1004() {
        Student s = new Student();
        s.setName("w1");
        s.setId(1);
        s.setGrade(2);
//        s.setMoney(3L);
        s.setTitle("t1");
        s.setFlag(true);

        Map<String, Object> map = BeanUtil.beanToMap(s);
        System.out.println("map = " + map);

    }

    @Test
    public void Test1020() {
        Map<String, String> map = new HashMap<>();
        map.put("w1","s1");
        map.put("w2","s2");
        map.put("w3","s3");
        map.put("w4","s4");

        /*map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                System.out.println("key: " + s +"   value:" + s2);
            }
        });*/

        map.forEach((k,v)->{
            System.out.println("key: " + k +"   value:" + v);
        });

        map.replaceAll((k,v)->v.toUpperCase());
        System.out.println(map);

    }

    @Test
    public void Test1045() {
        Map<String, Integer> m1 = new HashMap<>();
        m1.put("w1",1);
        m1.put("w2",2);
        m1.put("w3",3);

        Map<String, Integer> m2 = new HashMap<>();
        m2.put("w2",2);
        m2.put("w3",3);
        m2.put("w4",4);

    }

    @Test
    public void Test1064() {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("w1","v1");

    }

    /**
     * 在1.8中ConcurrentHashMap的get操作全程不需要加锁，这也是它比其他并发集合比如hashtable、用Collections.synchronizedMap()包装的hashmap;安全效率高的原因之一。
     * get操作全程不需要加锁是因为Node的成员val是用volatile修饰的和数组用volatile修饰没有关系。
     * 数组用volatile修饰主要是保证在数组扩容的时候保证可见性。
     */
    @Test
    public void Test1076() {
        Map<String, String> map = new HashMap<>();
        System.out.println("map.get(\"w1\") = " + map.get("w1"));
        if (Objects.nonNull(map.get("w1"))) {
            System.out.println("w1 exist");
        } else {
            System.out.println("w1 not exist");
        }

    }

    @Test
    public void Test1088() {
        Map<String, String> map = new HashMap<>();
        map.put(null, null);

    }

    @Test
    public void Test1095() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.put(6, 6);
        map.put(7, 7);
        map.put(8, 8);
        map.put(9, 9);
        map.put(10, 10);
        map.put(11, 11);
        map.put(12, 12);

        List<Integer> list = map.values().stream().skip(2).collect(Collectors.toList());
        System.out.println("list = " + list);

    }

    @Test
    public void Test1116() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("w1", 1);
        map.put("w2", 2);
        map.put("w3", 3);
        map.put("w4", 4);
        map.put("w5", 5);
        map.put("w6", 6);
        map.put("w7", 7);
        map.put("w8", 8);
        map.put("w9", 9);
        map.put("w10", 10);
        map.put("w11", 11);
        map.put("w12", 12);

        List<Integer> list = map.values().stream().skip(2).collect(Collectors.toList());
        System.out.println("list = " + list);

        Map<String, Integer> map1 = new HashMap<>();
        map1 = map;
        System.out.println("map1 = " + map1);
    }

    @Test
    public void Test1140() {
        Map<Integer, Long> map = new HashMap<>();
        map.put(1, 1L);
        map.put(2, 2L);
        map.put(3, 3L);


        long value  = map.values().stream().skip(22).mapToLong(Long::longValue).sum();
        System.out.println("value = " + value);

    }

    @Test
    public void Test1153() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("w1");

        Student s2 = new Student();
        s2.setId(1);
        s2.setName("w2");

        list.add(s1);
        list.add(s2);

//        Map<Integer,String> map = list.stream().collect(Collectors.toMap(Student::getId, Student::getName));
        Map<Integer,String> map1 = list.stream().collect(Collectors.toMap(Student::getId, Student::getName, (l1,l2)->l1));
        System.out.println("map1 = " + map1);


    }

    @Test
    public void Test1174() {
        Map<String, String> map = new TreeMap<>(new MapKeyComparator());
        map.put("w3","1");
        map.put("w5","2");
        map.put("w1","3");
        System.out.println("map = " + map);

        Map<String, String> map1 = new LinkedHashMap<>();
        map1.put("w3","1");
        map1.put("w5","2");
        map1.put("w1","3");
        System.out.println("map1 = " + map1);



    }

    @Test
    public void Test1192() {
        Map<String, String> map = new HashMap<>();
        map.put("w1","v1");
        map.put("w2","v2");
        map.put("w3","v3");

        System.out.println("map = " + map);

        map.clear();
        System.out.println("map = " + map);

    }

    @Autowired
    @Resource

    @Test
    public void Test1206() {
        Map<String, String> map = new HashMap<>();
        map.put("w1","v1");
        System.out.println(" 1 map = " + map);
        handleMap(map);
        System.out.println(" 2 map = " + map);

    }

    private void handleMap(Map<String, String> map) {
        map.clear();
        return;
    }
    @Test
    public void Test1225() {
        Map<String, String> map = new HashMap<>();
        map.put("token","1234567");
        System.out.println("map.remove(\"token\") = " + map.remove("token"));

    }

    @Test
    public void Test1233() {
        List<String> animals = Arrays.asList("dog", "cat", "cat", "dog", "fish", "dog");
        Map<String, Integer> map = new HashMap<>();
        for(String animal : animals){
            map.compute(animal, (k, v) -> v == null ? 1 : ++v);
        }
        System.out.println(map);

    }

    @Test
    public void Test1243() {
        HashMap<String, String> map = new HashMap<>();
        map.put("w1","v1");
        map.put("w2","v2");
        map.put("w3","v3");
        System.out.println("map = " + map);
        map.put("sign",getSign(map, "j0CQKP1B1G6XuQLsJy0YrhkkpGKcdeMY"));
        System.out.println("map = " + map);

    }

    public static String getSign(HashMap<String, String> paramsMap, String secretKey) {
        List<String> keys = new ArrayList<>(paramsMap.keySet());
        Collections.sort(keys);
        StringBuilder signCalc = new StringBuilder();
        for (String key : keys) {
            LOGGER.info("[getSign]>>> key:{} value:[{}]", key, paramsMap.get(key));
            signCalc.append(paramsMap.get(key));
        }
        return DigestUtils.md5Hex(signCalc.append(secretKey).toString()).toLowerCase();
    }

    @Test
    public void Test1267() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("w1",123);

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("w2","123");
        System.out.println("map = " + map);
        System.out.println("JSON.toJSONString(map) = " + JSON.toJSONString(map));

        //getSign(map, "j0CQKP1B1G6XuQLsJy0YrhkkpGKcdeMY")


    }
}
