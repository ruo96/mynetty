package com.wrh.basicUse;

import akka.remote.artery.aeron.TaskRunner;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.wrh.basicUse.vo.ChangVo;
import com.wrh.basicUse.vo.StudentVo;
import com.wrh.elasticsearch.Student;
import com.wrh.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.storm.command.list;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:44 2019/10/12 0012
 * @Modified By:
 */
@Slf4j
public class TestBasicUse {

    /**
     * String对象的intern方法会得到字符串对象在常量池中对应的版本的引用（如果常量池中有一个字符串与String对象的equals结果是true），
     * 如果常量池中没有对应的字符串，则该字符串将被添加到常量池中，然后返回常量池中字符串的引用。
     */
    @Test
    public void test(){
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());
    }

    @Test
    public void test1(){
        ChangVo changVo = new ChangVo();
        changVo.setX("X");
        changVo.setY("Y");
        changVo.setA(1);
        changVo.setB(2);

        log.info("=== 1： {}", JSON.toJSONString(changVo));
        changeValue1(changVo);
        log.info("=== 2： {}", JSON.toJSONString(changVo));

    }

    private void changeValue(ChangVo changVo) {

        changVo.setA(changVo.getA() + changVo.getB());
        changVo.setB(changVo.getA() - changVo.getB());
        changVo.setA(changVo.getA() - changVo.getB());
    }

    /**
     * 这种异或的方式最好
     * @param changVo
     */
    private void changeValue1(ChangVo changVo) {

        changVo.setA(changVo.getA()^changVo.getB());
        changVo.setB(changVo.getA()^changVo.getB());
        changVo.setA(changVo.getA()^ changVo.getB());
    }

    @Test
    public void test2(){
        Map<String, Map<String , String>> map = new HashMap<>();
        Map<String , String> map1 = new HashMap<>();
        map1.put("m1","123");
        map.put("wrh",map1);

        Map<String, String> map2 = map.get("wrh");
        map2.put("m2","456");

        log.info("===> map2: {}",map2);
        log.info("===> map: {}",map);
    }

    @Test
    public void test3(){
        int i = 1;
        Integer j = 1;
        log.info("===> i ==j :{}", i==j);

        int i1 = 2000;
        Integer j1 = 2000;
        log.info("===> i1 ==j1 :{}", i1==j1);

        int i2 = 2000;
        Integer j2 = new Integer(2000);
        log.info("===> i2 ==j2 :{}", i2==j2);

        int i3 = 2000;
        Integer j3 = new Integer(2000);
        log.info("===> j3==i3 :{}", j3==i3);
    }


    @Test
    public void test4(){
//        Integer i =null;
//        int j = i;
        log.info("===> {}",Runtime.getRuntime().availableProcessors());

    }

    @Test
    public void test5(){
        Integer i = 12;
        Integer j = 12;

        log.info("+++>{}",i == j);

        Integer i1 = 129;
        Integer j1 = 129;

        log.info("+++>{}",i1 == j1);
        log.info("+++>{}",i1.equals(j1));

    }

    @Test
    public void test6(){
        BigDecimal a = new BigDecimal("1.3336");
        BigDecimal b = new BigDecimal("0.9");
        log.info("===>{}",a.compareTo(b) );

        BigDecimal c = a.setScale(3,BigDecimal.ROUND_HALF_DOWN);
        BigDecimal d = a.setScale(3,BigDecimal.ROUND_HALF_UP);
        BigDecimal e = a.setScale(3,BigDecimal.ROUND_HALF_EVEN);
        BigDecimal f = a.setScale(3,BigDecimal.ROUND_DOWN);
        BigDecimal g = a.setScale(3,BigDecimal.ROUND_CEILING);
        BigDecimal h = a.setScale(3,BigDecimal.ROUND_FLOOR);
        log.info("=c==>{}",c );
        log.info("=d==>{}",d );
        log.info("=e==>{}",e );
        log.info("=f==>{}",f );
        log.info("=g==>{}",g );
        log.info("=h==>{}",h );

    }

    @Test
    public void test7(){
        BigDecimal a = BigDecimal.valueOf(0.1);
        BigDecimal b = BigDecimal.valueOf(0.09);
        log.info("===>{}", a.subtract(b));

    }

    @Test
    public void test8(){
        List<String> list = Arrays.asList("1","2","3");
        log.info("===>{}", JSON.toJSONString(list));

        //这个是不支持的
//        list.add("4");
//        log.info("===>{}", JSON.toJSONString(list));

        List<String> list1 = new ArrayList<>(list);
        log.info("===>{}", JSON.toJSONString(list1));
        list1.add("4");
        log.info("===>{}", JSON.toJSONString(list1));

    }

    @Test
    public void test9(){
        String  a  = "123";
        String  b  = a.intern();
        log.info("===>{}", a == b);
    }

    @Test
    public void test10(){
        Integer a = 20;

        String ahex = Integer.toHexString(a);
        log.info("===>{}",ahex);
        log.info("===>toBinaryString {}",Integer.toBinaryString(a));
        log.info("===>toOctalString {}",Integer.toOctalString(a));
        log.info("===>toUnsignedString radix 8 {}",Integer.toUnsignedString(a,8));
        log.info("===>toUnsignedString {}",Integer.toUnsignedString(a));
    }

    @Test
    public void test11(){
        String a = "a";
        log.info("===> hashcode: {}",a.hashCode());

        Integer b = 123;
        log.info("===> hashcode: {}",b.hashCode());

        Integer c = new Integer(1112);
        log.info("===> hashcode: {}",c.hashCode());

        ChangVo vo = new ChangVo();
        vo.setX("1");
        vo.setY("2");
        vo.setA(3);
        vo.setB(4);
        log.info("===> vo hashcode: {}",vo.hashCode());

        ChangVo vo0 = new ChangVo();
        vo0.setX("1");
        vo0.setY("2");
        vo0.setA(3);
        vo0.setB(4);
        log.info("===> vo0 hashcode: {}",vo0.hashCode());

        StudentVo vo1 = new StudentVo();
        vo1.setName("w1");
        vo1.setAge(1);

        log.info("===> vo1 hashcode: {}",vo1.hashCode());
        StudentVo vo2 = new StudentVo();
        vo2.setName("w1");
        vo2.setAge(1);
        log.info("===> vo2 hashcode: {}",vo2.hashCode());


    }

    @Test
    public void test12(){
        testStrings(null);
    }

    private void testStrings(String... str){
        if(Objects.isNull(str)){
            log.info(" str is null");
        }else {
            log.info("===>{}",str[0]);
        }
        int i = 2 * 3;
    }

    /**
     * 自己设计一个泛型的获取数组最小值的函数，并且这个方法只能接受Number的子类并且实现了Compparable的接口
     */
    @Test
    public void test13(){
        int minInteger = min(new Integer[]{1,2,3});
        double minDouble = min(new Double[]{1.2,2.2,-1d});
//        String typeError = min(new String[]{"1","2"});

        log.info("===>{}",minInteger);
        log.info("===>{}",minDouble);
    }

    private static <T extends Number & Comparable<? super T>> T min(T[] values){
        if(values == null || values.length == 0) {
            return null;
        }
        T min = values[0];
        for (int i = 1; i < values.length ; i++) {
            if(min.compareTo(values[i]) > 0) {
                min = values[i];
            }
        }
        return min;
    }
    
    @Test
    public void test14(){

        Integer a = new Random().nextInt(10);
        System.out.println(a);
    }

    /*private Class test999() {

        *//*public void add(){
            System.out.println();
        }*//*
    }*/

    @Test
    public void test15(){
        ChangVo vo = new ChangVo();
        vo.setX("1");
        vo.setY("2");
        vo.setA(3);
        vo.setB(4);

        changeV(vo);
        System.out.println(vo);


    }

    private void changeV(ChangVo vo) {
        //这个地方就可以看到是值引用了
        vo = new ChangVo();
        vo.setA(999);
    }

    @Test
    public void test16(){
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());

    }

    private static AtomicInteger nextHashCode =
            new AtomicInteger();

    private static final int HASH_INCREMENT = 0x61c88647;

    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    @Test
    public void test17(){



            for (int i = 0; i < 16; i++) {
                System.out.print(nextHashCode() & 15);
                System.out.print(" ");
            }
            System.out.println();
            for (int i = 0; i < 32; i++) {
                System.out.print(nextHashCode() & 31);
                System.out.print(" ");
            }
            System.out.println();
            for (int i = 0; i < 64; i++) {
                System.out.print(nextHashCode() & 63);
                System.out.print(" ");
            }


    }

    @Test
    public void Test() {
        for (int i = 0; i < 10; i++) {
            try {
                int j = (i+5)/(i-5);
            } catch (Exception e) {
                System.out.println(e);
                continue;
            }
            System.out.println(i);
        }
    }

    @Test
    public void Test1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
                int j = (i+5)/(i-5);

        }
    }

    @Test
    public void Test2() {
        float a = 0.125f;
        double b = 0.125d;
        System.out.println((a - b) == 0);  // true

        double c = 0.8;
        double d = 0.7;
        double e = 0.6;
        System.out.println((c - d) == (d - e));  //false

        System.out.println(1.0/0);  // Infinity
//        System.out.println(1/0);  // java.lang.ArithmeticException: / by zero
        System.out.println(1.0/0.0); // Infinity
    }

    @Test
    public void Test3() {
        System.out.println(16>>1);
        System.out.println(16>>>1);

        System.out.println(-16>>1);   //-8
        System.out.println(-16>>>1);  //2147483640

//        System.out.println(1.2>>1);  // 浮点不能用
//        System.out.println(1.2>>>1); // 浮点不能用
    }

    @Test
    public void Test4() {
        f((Integer) null);  // 入参必须要指定
    }

    private void f(String o) {
        System.out.println("enter String method");
    }

    private void f(Integer o) {
        System.out.println("enter Integer method");
    }

    @Test
    public void Test5() {
        for (int i = 0; i < 10; i++) {
            g(1);
        }
    }

    private void g(double i) {  //为什么会直接用这个？
        System.out.println("enter double method");
    }

    private void g(Integer i) {
        System.out.println("enter Integer method");
    }

    @Test
    public void Test6() {
        String a = null;
        switch (a) {    // java.lang.NullPointerException
            case "null":
                System.out.println("enter string null");
                break;
            case "1":
                System.out.println("enter string 1");
                break;
            default:
                System.out.println("enter default");
                break;
        }

    }

    @Test
    public void Test7() {
        System.out.println(get("123",new HashMap(4)));
    }

    <String, T> String get(String s, T t) {   // 可以通过
        return s;
    }

    @Test
    public void Test8() {
        log.info("first use no conflict");
    }

    @Test
    public void Test9() {
        Integer i = 200;
        Integer j = 200;
        if (i.equals(j)) {
            System.out.println("equal");
        } else {
            System.out.println(" not equal");
        }
    }

    @Test
    public void Test10() {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");

        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);

        if(x.equals(y)){
            System.out.println("true");
        }

    }

    @Test
    public void Test11() {
        ArrayList<Integer> li = new ArrayList<Integer>();
        ArrayList<Float> lf = new ArrayList<Float>();
        if(li.getClass() == lf.getClass()) {
            System.out.println("666");
        }

    }

    @Test
    public void Test12() {
        double[] d = new double[10];
        System.out.println(d[0]);
        System.out.println(d[1]);
        System.out.println(d[2]);
        System.out.println(d.length);
        System.out.println(d[0] * d[1] == 0);
    }

    @Test
    public void Test13() {
        double[] d = new double[4];
        d[0] = 1;
        d[1] = 2;
        d[2] = 3;
        System.out.println(d[0]);
        System.out.println(d.length);
    }

    @Test
    public void Test14() {
        String year = "2020-0202";
        System.out.println(year.substring(0,4));
    }

    @Test
    public void Test15() {
        String a = "  ";
        System.out.println(StringUtils.isEmpty(a));
        System.out.println(StringUtils.isBlank(a));
    }

    @Test
    public void Test16() {
        Student student = new Student();
        student.setName("w1");
        student.setId(1);
        student.setGrade(100);
        System.out.println(JSON.toJSONString(student));

        String str = "{\"grade1\":100,\"id1\":1,\"name1\":\"w1\"}";
        Student student1 = JSON.parseObject(str, Student.class);
        if(Objects.isNull(student1)) {
            System.out.println("null");
        }else {
            System.out.println(JSON.toJSONString(student1));
        }
    }

    @Test
    public void Test17() {
        String str = "{\"grade1\":100,\"id1\":1,\"name1\":\"w1\"}";
        GameDayData gameDayData = JSON.parseObject(str, GameDayData.class);
        GameDayData gameDayData1 = new Gson().fromJson(str, GameDayData.class);
        if(Objects.isNull(gameDayData1)) {
            System.out.println("null");
        }else {
            System.out.println(JSON.toJSONString(gameDayData1));
        }
    }

    @Test
    public void Test18() {
        long timestamp = 0;
        String date = "";
        for (int i = 0; i < 10 ; i++) {
            timestamp += i;
            date = date + i;
            System.out.println( timestamp );
            System.out.println(date );
        }
    }

    @Test
    public void Test19() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        list.add("789");
        System.out.println(JSON.toJSONString(list));
        String listStr = JSON.toJSONString(list);

        List<String> list2 = JSON.parseArray(listStr, String.class);
        list2.stream().forEach(e-> System.out.println(e));
    }

    @Test
    public void Test585() {
        Student s = new Student();
        String name = s.getName();
        if(Objects.isNull(name)){
            System.out.println("null name");
        }
    }
    @Test
    public void Test593() {
        String newLine = System.getProperty("line.separator");
        System.out.println("["+newLine+"]");

    }

    @Test
    public void Test600() {
        List<Long> list1 = new ArrayList<>();
        long a = 123;
        list1.add(a);
        System.out.println(list1);
        a = 456;
        System.out.println(list1);

    }

    @Test
    public void Test611() {
        long a = getNull();
        System.out.println(a); // 会空指针
    }

    private Long getNull() {
        return null;
    }

    @Test
    public void Test621() {
        Integer a = null;
        if(a == null){
            System.out.println("a is null");
        }else {
            System.out.println("no");
        }

    }



    @Test
    public void Test595() {
        String a = "-1";
        if(StringUtils.isNumeric(a)){
            System.out.println("yes");
        }else {
            System.out.println("now");
        }
    }

    @Test
    public void Test645() {
        Student s = new Student();
        s.setName("w1");
        s.setGrade(123);

        System.out.println(s);

        s.setGrade(null);
        System.out.println(s);

    }

    @Test
    public void Test659() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
        System.out.println("===============");
        for (int i = 0; i < 5; ++i) {
            System.out.println(i);
        }

    }

    @Test
    public void Test672() {
        Student s = new Student();
        s.setId(1);
        System.out.println(s);
        if (s.getId().equals(1)) {
            System.out.println("equal 1 ");
        } else {
            System.out.println("not equal 1");
        }

    }

    @Test
    public void Test685() {
        List<String> list = new ArrayList<>();
        list.add("w1");
        list.add("w2");
        list.add("w3");

        String CSV_CONCAT = "`";
        String SQL_CONCAT = "toString(%s),'%s'";
        list = list.stream().map(e->String.format(SQL_CONCAT, e, CSV_CONCAT)).collect(Collectors.toList());

        String columnRaw = StringUtils.join(list.toArray(), ",");
        System.out.println("columnRaw = " + columnRaw);


    }

    @Test
    public void Test704() {
        StringJoiner joiner = new StringJoiner(File.separator);
        String path = joiner.add("data").add("psi")+".csv";
        System.out.println("path = " + path);

    }

    @Test
    public void Test713() {
        String a = "123";
        a = a + "abc";
        System.out.println("a = " + a);

    }
    
    @Test
    public void Test721() {
        String jsonString = "[{\"auth\":\"PLAINTEXT_AFTER_JOIN\",\"columns\":[\"id\",\"x0\"]},{\"auth\":\"PLAINTEXT_AFTER_GROUP_BY\",\"columns\":[\"x1\",\"x2\",\"type\"]},{\"auth\":\"PLAINTEXT_AFTER_AGGREGATE\",\"columns\":[\"x3\",\"x4\"]},{\"auth\":\"PLAINTEXT_AFTER_COMPARE\",\"columns\":[\"x5\",\"x6\"]}]";

        Map<String, List<String>> resultMap = parseJsonString(jsonString);
        System.out.println(resultMap);

        Map<String, List<String>> resultMap1 = parseJsonStringByFastjson(jsonString);
        System.out.println(resultMap1);
      
    }

    private Map<String, List<String>> parseJsonStringByFastjson(String jsonString) {
        JSONArray jsonArray = JSON.parseArray(jsonString);
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String auth = jsonObject.getString("auth");
            JSONArray columnsArray = jsonObject.getJSONArray("columns");
            List<String> columnsList = new ArrayList<>();

            for (int j = 0; j < columnsArray.size(); j++) {
                String column = columnsArray.getString(j);
                columnsList.add(column);
            }

            map.put(auth, columnsList);
        }

        return map;
    }

    private static Map<String, List<String>> parseJsonString(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<String>> resultMap = new HashMap<>();

        try {
            JsonNode rootNode = objectMapper.readTree(jsonString);
            for (JsonNode node : rootNode) {
                String auth = node.get("auth").asText();
                JsonNode columnsNode = node.get("columns");
                List<String> columns = objectMapper.readValue(columnsNode.toString(), List.class);
                resultMap.put(auth, columns);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    @Test
    public void Test752() {
        String jsonString = "{\n" +
                "    \"cols\":[\n" +
                "        {\n" +
                "            \"colName\":\"id\",\n" +
                "            \"constraint\":\"PLAINTEXT_AFTER_JOIN\",\n" +
                "            \"to\":\"null\",\n" +
                "            \"type\":\"string\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"colName\":\"x0\",\n" +
                "            \"constraint\":\"PLAINTEXT_AFTER_JOIN\",\n" +
                "            \"to\":\"null\",\n" +
                "            \"type\":\"float\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"colName\":\"x1\",\n" +
                "            \"constraint\":\"PLAINTEXT_AFTER_GROUP_BY\",\n" +
                "            \"to\":\"null\",\n" +
                "            \"type\":\"float\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"colName\":\"x2\",\n" +
                "            \"constraint\":\"PLAINTEXT_AFTER_GROUP_BY\",\n" +
                "            \"to\":\"null\",\n" +
                "            \"type\":\"float\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"colName\":\"type\",\n" +
                "            \"constraint\":\"PLAINTEXT_AFTER_GROUP_BY\",\n" +
                "            \"to\":\"null\",\n" +
                "            \"type\":\"float\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"colName\":\"x3\",\n" +
                "            \"constraint\":\"PLAINTEXT_AFTER_AGGREGATE\",\n" +
                "            \"to\":\"null\",\n" +
                "            \"type\":\"float\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"colName\":\"x4\",\n" +
                "            \"constraint\":\"PLAINTEXT_AFTER_AGGREGATE\",\n" +
                "            \"to\":\"null\",\n" +
                "            \"type\":\"float\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"colName\":\"x5\",\n" +
                "            \"constraint\":\"PLAINTEXT_AFTER_COMPARE\",\n" +
                "            \"to\":\"null\",\n" +
                "            \"type\":\"float\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"colName\":\"x6\",\n" +
                "            \"constraint\":\"PLAINTEXT_AFTER_COMPARE\",\n" +
                "            \"to\":\"null\",\n" +
                "            \"type\":\"float\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"dbName\":\"p_6702757040588066816\",\n" +
                "    \"tblName\":\"d_6702764461452693504\"\n" +
                "}";

        JSONObject json = JSONObject.parseObject(jsonString);
        JSONArray cols = json.getJSONArray("cols");

        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < cols.size(); i++) {
            JSONObject col = cols.getJSONObject(i);
            String colName = col.getString("colName");
            String constraint = col.getString("constraint");

            if (!map.containsKey(constraint)) {
                map.put(constraint, new ArrayList<>());
            }

            map.get(constraint).add(colName);
        }

        // Print the map
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String constraint = entry.getKey();
            List<String> colNames = entry.getValue();

            System.out.println("Constraint: " + constraint);
            System.out.println("ColNames: " + colNames);
        }

    }


    @Test
    public void Test870() {
        Map<String, List<String>> map1 = new HashMap<>();
        map1.put("key1", Arrays.asList("value1", "value2"));
        map1.put("key2", Arrays.asList("value3"));

        Map<String, List<String>> map2 = new HashMap<>();
        map2.put("key1", Arrays.asList("value2", "value1"));
        map2.put("key2", Arrays.asList("value3"));

        boolean isEqual = checkMapsEquality(map1, map2);
        System.out.println("Are the maps equal? " + isEqual);
      
    }


    private static boolean checkMapsEquality(Map<String, List<String>> map1, Map<String, List<String>> map2) {
        if (map1.size() != map2.size()) {
            return false; // 如果大小不同，则不相等
        }

        for (String key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                return false; // 如果第二个Map不包含相同的键，不相等
            }

            List<String> value1 = map1.get(key);
            List<String> value2 = map2.get(key);

            if (!value1.equals(value2)) {
                return false; // 如果键对应的值列表不相等，不相等
            }
        }

        return true; // 如果经过所有检查，两个Map相等
    }
    
    @Test
    public void Test907() {
        List<String> list1 = new ArrayList<>();
        list1.add("apple");
        list1.add("banana");
        list1.add("cherry");

        List<String> list2 = new ArrayList<>();
        list2.add("banana");
        list2.add("cherry");
//        list2.add("apple1");

        Set<String> set1 = new HashSet<>(list1);
        Set<String> set2 = new HashSet<>(list2);

        System.out.println("list1.equals(list2) = " + set1.equals(set2));
        System.out.println("LocalDateTime.now().toString() = " + LocalDateTime.now().toString());
        System.out.println("LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) = " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

    }

    @Test
    public void Test930() {
        String a = "w123";
        byte[] bytes = a.getBytes(StandardCharsets.UTF_8);

        for (int i = 0; i < bytes.length; i++) {
            System.out.println("first bytes[i] = " + bytes[i]);
        }

        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
//        buffer.order(ByteOrder.BIG_ENDIAN);

        byte[] littleEndBytes = new byte[bytes.length];

        buffer.get(littleEndBytes);

        for (byte b : littleEndBytes) {
//            System.out.println("b = " + b);
            System.out.println("Integer.toHexString(b &0xFF) = " + (Integer.toHexString(b & 0xFF)+""));
            System.out.println("Integer.toHexString(b) = " + Integer.toHexString(b));
        }



    }

}
