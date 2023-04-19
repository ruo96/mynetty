package com.wrh.list;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.wrh.collection.map.GameRealTimeData;
import com.wrh.collection.map.vo.GameDayDataV2;
import com.wrh.elasticsearch.Student;
import com.wrh.list.vo.DataList;
import com.wrh.list.vo.GameConfig;
import com.wrh.list.vo.TotalInfo;
import com.wrh.list.vo.TotalYearKpi;
import com.wrh.reflection.S;
import com.wrh.utils.GsonUtils;
import com.wrh.utils.test.Dog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:05 2019/1/9 0009
 * @Modified By:
 */
@Slf4j
public class TestList {
    public static void main(String[] args) {
        ArrayList<String> listOne = new ArrayList<String>();
        listOne.add("this is list");

        Vector<String> vector = new Vector<String>();
        vector.add("this is list");
        System.out.println(listOne.equals(vector));

        handleList(listOne);
        System.out.println("handle: " + listOne);
    }

    private static void handleList(List list){
        list.add("123");
    }

    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        Vector<String> vector = new Vector<>();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        vector.add("3");

        System.out.println(list.equals(vector));
    }

    @Test
    public void Test() {
        Dog d1 = new Dog();
        d1.setColor("1");
        d1.setName("2");
        d1.setAge(3);
        d1.setOwner("4");

        List<Dog> list = new ArrayList<>();
        list.add(d1);

        Dog d2 = new Dog();
        BeanUtils.copyProperties(d1,d2);
        d2.setColor("5");

        list.add(d2);
        log.info(">>> Gson: {}", GsonUtils.GSON.toJson(list));
        log.info(">>> Long: {}", Long.valueOf("0"));
    }

    @Test
    public void Test1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        log.info("{}",list);
    }

    @Test
    public void Test2() {
        Integer list;

        list = returnNull();

        log.info("{}", Objects.isNull(list));
    }

    private Integer returnNull() {
        return null;
    }

    @Test
    public void Test3() {
        List<String> list = new ArrayList<>();
        List<String> list1 = list;
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list.add("4");
        System.out.println(list1);
        System.out.println(list);
    }
    @Test
    public void Test4() {
        List<GameConfig> list = new ArrayList<>();
        GameConfig g1 = new GameConfig();
        g1.setGameId(1);
        g1.setGroupId(1);

        GameConfig g2 = new GameConfig();
        g2.setGameId(2);
        g2.setGroupId(2);

        GameConfig g3 = new GameConfig();
        g3.setGameId(3);
        g3.setGroupId(3);

        list.add(g1);
        list.add(g2);
        list.add(g3);

        StringBuilder sb = new StringBuilder();
        list.stream().forEach(e->{
            sb.append(e.getGameId()).append(":").append(e.getGroupId()).append("-");
        });
        System.out.println(sb.toString());
        String str = sb.toString();
        System.out.println("str length: "+str.length());

        Map<String, String> map = new HashMap<>();
        map.put("1", str.substring(0,str.length()-1));
        System.out.println("map is : " + map);
        String str1 = str.substring(0, str.length() - 1);

        System.out.println("after substring: "+str1);
        String[] newStr = str1.split("-");
        System.out.println("newStr:"+ JSON.toJSONString(newStr));
        System.out.println("length:"+newStr.length);

        String[] config = str1.split("-");
        List<GameConfig> finalGameConfigs = new ArrayList<>();
        Arrays.asList(config).stream().forEach(e->{
            String[] single = e.split(":");
            GameConfig g = new GameConfig();
            g.setGameId(Integer.valueOf(single[0]));
            g.setGroupId(Integer.valueOf(single[1]));
            finalGameConfigs.add(g);
        });
        System.out.println(finalGameConfigs);


    }

    @Test
    public void Test5() {
        List<TotalYearKpi> totalYearKpis = new ArrayList<>();
        Map<Integer, List<TotalYearKpi>> yearKpiMap = new HashMap<>();
        if(CollectionUtils.isNotEmpty(totalYearKpis)) {
            yearKpiMap = totalYearKpis.stream().collect(Collectors.groupingBy(TotalYearKpi::getGroupId));
        } else {
            log.info(">>> queryRechargeResultDetail TotalYearKpi is null,");
        }
        System.out.println(yearKpiMap);
        if(yearKpiMap.containsKey("w1")) {
            System.out.println("has w1");
        }else {
            System.out.println("not has w1");
        }
    }

    @Test
    public void Test6() {
        List<GameConfig> gameConfigs = new ArrayList<>();
        handleGameConfigList(gameConfigs);
        System.out.println(gameConfigs);
    }

    private void handleGameConfigList(List<GameConfig> gameConfigs) {
        List<GameConfig> list = gameConfigs;
        GameConfig g = new GameConfig();
        g.setGameId(0);
        g.setGroupId(0);
        list.add(g);
        return;
    }

    @Test
    public void Test7() {
        List<String> list = new ArrayList<>();
        list.add("w1");
        list.add("w7");
        list.add("w2");
        list.add("w3");
        list.add("w4");
        list.add("w5");
        list.add("w6");

        System.out.println(list);
    }

    @Test
    public void Test8() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setId(1);
        s1.setGrade(1);

        Student s2 = new Student();
        s2.setName("w2");
        s2.setId(2);
        s2.setGrade(2);

        Student s3 = new Student();
        s3.setName("w3");
        s3.setId(3);
        s3.setGrade(3);


        list.add(s1);
        list.add(s2);
        list.add(s3);


        System.out.println(list);

        list.stream().forEach(e->{
            if("w2".equals(e.getName())){
                e.setId(999);
            }

        });
        System.out.println(list);
    }

    @Test
    public void Test9() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        String str = StringUtils.join(list,"-");
        System.out.println(str);
    }

    private static List<String> list = new ArrayList<>(10);

    @Test
    public void Test10() {
        List<String> list = new ArrayList<>();
        while (true){
            if(list.size() < 10){
                list.add("w"+list.size());
            }else {
                list.clear();
            }
            System.out.println(list);
        }
    }

    @Test
    public void Test11() {
        List<String> list = new ArrayList<>();
        System.out.println(list.size());
        list.add("w1");
        System.out.println(list.size());
        list.add("w2");
        System.out.println(list.size());
    }

    private static long lastTimeStamp = System.currentTimeMillis();
    @Test
    public void Test12() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            System.out.println(lastTimeStamp);
            TimeUnit.SECONDS.sleep(1);

        }
    }

    public static List<String> getStringList(){
        List<String> list = new ArrayList<>();
        list.add("w1");
        list.add("w2");
        list.add("w3");
        return list;
    }

    public List<Integer> getIntegerList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }

    @Test
    public void Test296() {
        List<Integer> list  = new ArrayList<>();
        final Long[] paySumMoneyArr = {0L};
        list.stream().forEach(e->{
            paySumMoneyArr[0] += e;
        });
        System.out.println(paySumMoneyArr[0]);



    }

    @Test
    public void Test325() {
        List<Student> list = getStudentList();
        log.info(">>> {}", JSON.toJSONString(list));

        Map<String, List<Student>> map1 = list.stream().collect(Collectors.groupingBy(v-> v.getName()+"_"+v.getId()));
        log.info(">>> {}", JSON.toJSONString(map1));


    }

    @Test
    public void Test336() {
        List<Student> list = getStudentList();
        Optional<Student> max = list.stream().max(Comparator.comparing(e->e.getId()));
        if(max.isPresent()){
            System.out.println(max.get());
            System.out.println(max.get());
        }else {
            System.out.println("null");
        }

    }

    /**
     * 分组
     */
    @Test
    public void Test349() {
        List<Student> list = getStudentList();
        Map<Boolean, List<Student>> map = list.stream().collect(Collectors.partitioningBy(e->e.getId()>1));
        System.out.println(map);
    }

    /**
     * 拼接 有前后缀得， 有只有中间得， 有什么都没有直接拼接得
     */
    @Test
    public void Test359() {
        List<Student> list = getStudentList();
        String str = list.stream().map(Student::getName).collect(Collectors.joining("-","--","---"));
        String str1 = list.stream().map(Student::getName).collect(Collectors.joining("-"));
        String str2 = list.stream().map(Student::getName).collect(Collectors.joining());
        System.out.println(str);
        System.out.println(str1);
        System.out.println(str2);

    }

    public static List<Student> getStudentList() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("w3");
        s1.setId(1);
        s1.setGrade(10);

        Student s2 = new Student();
        s2.setName("w3");
        s2.setId(2);
        s2.setGrade(1);

        Student s3 = new Student();
        s3.setName("w1");
        s3.setId(3);
        s3.setGrade(4);

        Student s4 = new Student();
        s4.setName("w4");
        s4.setId(4);
        s4.setGrade(4);

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);

        return list;
    }

    private List<Student> getNoRepeadStudentList() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setId(1);
        s1.setGrade(1);

        Student s2 = new Student();
        s2.setName("w2");
        s2.setId(2);
        s2.setGrade(2);

        Student s3 = new Student();
        s3.setName("w3");
        s3.setId(3);
        s3.setGrade(3);

        Student s4 = new Student();
        s4.setName("w4");
        s4.setId(4);
        s4.setGrade(4);

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);

        return list;
    }

    @Test
    public void test404() {
        /*List<Student> list = getStudentList();
        long sum = list.stream().map(e -> {
            if (e.getId() == 2) {
                e.setGrade(e.getGrade() * 2);
            }
        }).mapToInt(Student::getGrade).sum();*/

    }

    @Test
    public void Test415() {
        List<Student> list = getNoRepeadStudentList();
        System.out.println(list);

        List<Student> list2 = getNoRepeadStudentList();
        System.out.println(list2);

        Map<Integer, Integer> map = list2.stream().collect(Collectors.toMap(Student::getId, Student::getGrade, (key1, key2)->key2));
        System.out.println(map);
        list.stream().forEach(e->{
            e.setGrade(e.getGrade() + map.get(e.getId()));
        });
        System.out.println(list);

    }

    @Test
    public void Test462() {
        List<Student> list = getNoRepeadStudentList();
        System.out.println(list);
        System.out.println("---------");
        list.clear();
        System.out.println(list);
        System.out.println("---------");
        initStudentList(list);
        System.out.println(list);

        List<Student> list2 = new ArrayList<>();
        initStudentList(list2);
        System.out.println(list2);

    }

    void initStudentList(List<Student> list){
//        list = new ArrayList<>();
        Student s = new Student();
        s.setName("w999");
        s.setId(999);
        s.setGrade(999);
        list.add(s);


    }

    List<Integer> getIntegerList2(){
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);
        return l1;
    }

    List<Integer> getIntegerList3(){
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(5);
        return l1;
    }

    @Test
    public void Test490() {
        List<Integer> l1 = getIntegerList2();
        System.out.println("l1 is : " + l1);
        List<Integer> l2 = getIntegerList3();
        System.out.println("l2 is : " + l2);
        l1.removeAll(l2);
        System.out.println("===after===");
        System.out.println("l1 is : " + l1);
        System.out.println("l2 is : " + l2);
    }

    @Test
    public void Test520() {
        List<Integer> l1 = getIntegerList2();
        System.out.println("l1 is : " + l1);
        List<Integer> l2 = Arrays.asList(1);
        System.out.println("l2 is : " + l2);
        l1.removeAll(l2);
        System.out.println("===after===");
        System.out.println("l1 is : " + l1);
        System.out.println("l2 is : " + l2);

    }

    @Test
    public void Test533() {
        List<Integer> l1 = Arrays.asList(1,2,3,4,5,6,7);
        List<Integer> l2 = null;
        l1.removeAll(l2);
    }

    @Test
    public void Test540() {
        List<String> list = Stream.of("1","2","3").collect(Collectors.toList());
        System.out.println(list);

    }

    @Test
    public void Test548() {
        List<Student> list = getStudentList();
        System.out.println(list);

        /*for(Student s : list){
            if(s.getId().equals(1)){
                s.setName("少枪2韩版");
            }
        }*/

        list.stream().forEach(e->{if(e.getId().equals(1)){
            e.setName("少枪");
        }});

        System.out.println(list);

    }

    @Test
    public void Test567() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        List<Integer> list = new ArrayList<>(set);
        System.out.println(list);


    }

    private List<Integer> findNearSlotNum(Integer slotNum) {
        List<Integer> list = new ArrayList<>();
        if(Objects.isNull(slotNum)){
            return list;
        }
        list.add(slotNum);
        for (int i = 1; i < 5; i++) {
            if(slotNum - i >=0){
                list.add(slotNum - i);
            }
        }
        return list;
    }

    @Test
    public void Test594() {
        System.out.println(findNearSlotNum(1));
        System.out.println(findNearSlotNum(2));
        System.out.println(findNearSlotNum(3));
        System.out.println(findNearSlotNum(4));
        System.out.println(findNearSlotNum(5));
        System.out.println(findNearSlotNum(6));

    }

    @Test
    public void Test605() {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
//        System.out.println(CollectionUtils.ma);

        Long a = list.stream().max(Long::compareTo).get();
        Long b = list.stream().max((list1,list2)->list1>list2?1:-1).get();
        System.out.println(a);
        System.out.println(b);
    }

    /**
     * list倒叙
     */
    @Test
    public void Test620() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);

        list.sort(Comparator.reverseOrder());
        System.out.println(list);

    }

    @Test
    public void Test637() {
        List<SharpData> list = new ArrayList<>();
        SharpData s1 = new SharpData();
        s1.setTime("2020-07-20 01:00:00");
        s1.setNum(100L);
        list.add(s1);

        SharpData s2 = new SharpData();
        s2.setTime("2020-07-20 02:00:00");
        s2.setNum(200L);
        list.add(s2);

        SharpData s3 = new SharpData();
        s3.setTime("2020-07-20 03:00:00");
        s3.setNum(300L);
        list.add(s3);

        SharpData s9 = new SharpData();
        s9.setTime("2020-07-20 09:00:00");
        s9.setNum(900L);
        list.add(s9);

        SharpData s10 = new SharpData();
        s10.setTime("2020-07-20 10:00:00");
        s10.setNum(1000L);
        list.add(s10);

        SharpData s20 = new SharpData();
        s20.setTime("2020-07-20 20:00:00");
        s20.setNum(2000L);
        list.add(s20);

        SharpData s24 = new SharpData();
        s24.setTime("2020-07-20 24:00:00");
        s24.setNum(2400L);
        list.add(s24);

        System.out.println(list);

        List<SharpData> list1 = list.stream().sorted(Comparator.comparing(SharpData::getTime).reversed()).collect(Collectors.toList());
        System.out.println(list1);


    }

    @Test
    public void Test683() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.subList(5,0);

    }

    @Test
    public void Test694() {
        Student s1 = new Student();
        Student s2 = new Student();
        System.out.println(s1.getMoney());
        s2.setMoney(s1.getMoney());
        System.out.println(s2);



    }

    @Test
    public void test707() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        long m1 = Runtime.getRuntime().freeMemory()/1024;
        System.out.println("start memory: " + m1 + "kb");
        list.forEach((k)->{
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
    public void test730() throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        long m1 = Runtime.getRuntime().freeMemory() / 1024;
        System.out.println("start memory: " + m1 + "kb");
        for (Integer i1 : list) {
            int[] i = new int[1024 * 1024];
            for (int j = 0; j < 1024 * 1024; j++) {
                i[j] = j;
            }
            long m2 = Runtime.getRuntime().freeMemory() / 1024;
            System.out.println("this time memory: " + m2 + "kb");
            System.out.println(m1 - m2);
        }
        long m2 = Runtime.getRuntime().freeMemory() / 1024;
        System.out.println("finally");
        System.out.println(m1 - m2);

        TimeUnit.SECONDS.sleep(5);

        m2 = Runtime.getRuntime().freeMemory() / 1024;
        System.out.println(" real finally");
        System.out.println(m1 - m2);

        System.gc();
        m2 = Runtime.getRuntime().freeMemory() / 1024;
        System.out.println(" gc finally");
        System.out.println(m1 - m2);
    }
        @Test
       public void Test707() {
        List<Student> list = getStudentList();
        log.info(">>>before list: {}" ,list);
        list.stream().forEach(e->{
            if(StringUtils.isBlank(e.getTitle())){
                e.setTitle("无名英雄");
            }
        });
        log.info(">>>after list: {}" ,list);

    }

    @Test
    public void Test720() {
        String str = "   ";
        System.out.println(StringUtils.isBlank(str));
        System.out.println(StringUtils.isNotBlank(str));
        Student student = new Student();
        student.setTitle(null);

    }

    @Test
    public void Test786() {
        List<Integer> list = new ArrayList<>();
        if(list.contains(1)){
            System.out.println("yes");
        }else {
            System.out.println("no");
        }

    }

    @Test
    public void Test797() {
        List<Integer> list = getIntegerList();
        System.out.println(list);
        String str = JSON.toJSONString(list);
        System.out.println(str);
        List<Integer> list1 = JSON.parseArray(str, Integer.class);
        System.out.println(list1);

    }

    /**
     * list迭代器
     */
    @Test
    public void Test808() {
        List<Integer> list = getIntegerList();
        System.out.println(list);
        ListIterator<Integer> integerListIterator = list.listIterator(1);
        while (integerListIterator.hasNext()){
            System.out.println(integerListIterator.next());
        }

        System.out.println("============================");

        ListIterator<Integer> integerListIterator1 = list.listIterator(1);
        while (integerListIterator1.hasPrevious()){
            System.out.println(integerListIterator1.previous());
        }

    }

    @Test
    public void Test829() {
        List<Student> list = new ArrayList<>();
        Student s = new Student();
        s.setName("w1");
        s.setId(1);
        s.setGrade(2);
        s.setMoney(3L);
        s.setTitle("w1");

        for (int i = 0; i < 9; i++) {
            list.add(s);
        }

        System.out.println(list);


    }

    @Test
    public void Test848() {
        List<Integer> values = Stream.of(1, 3, 99, 234).collect(Collectors.toList());
        Integer maxValue = values.stream().max(Integer::compareTo).get();
        System.out.println(maxValue);

    }

    private List<String> getInitialDsList() {
        List<String> list1 = new ArrayList<>();
        list1.add("2020-09-01");
        list1.add("2020-09-02");
        list1.add("2020-09-03");
        list1.add("2020-09-04");
        return list1;
    }

    private List<String> getOtherDsList() {
        List<String> list1 = new ArrayList<>();
        list1.add("2020-09-03");
        list1.add("2020-09-08");
        list1.add("2020-09-05");
        list1.add("2020-09-06");
        return list1;
    }

    @Test
    public void Test856() {
        List<String> list1 = getInitialDsList();
        List<String> list2 = getOtherDsList();

        List<String> list = (List<String>) CollectionUtils.retainAll(list1,list2);
        String result = list.stream().sorted(Comparator.reverseOrder()).findFirst().orElse("no result");
        String updateTime = (String) CollectionUtils.retainAll(list1,list2).stream().sorted(Comparator.reverseOrder()).findFirst().orElse("wrong");
        System.out.println(list);
        System.out.println(result);
        System.out.println(updateTime);


    }

    @Test
    public void Test890() {
        List<String> list = new ArrayList<>();
        list.add("w1");
        list.add("w2");
        list.add("w3");
        System.out.println(list);
        list.remove("w2");
        System.out.println(list);

    }

    @Test
    public void Test902() {
        Student student = new Student();
        Student student1 = new Student();
        student1.setFlag(false);
        if(student.isFlag()){
            System.out.println("1 flag true");
        }else {
            System.out.println("1 flag false");
        }

        if(student1.isFlag()){
            System.out.println("2 flag true");
        }else {
            System.out.println("2 flag false");
        }


    }

    @Test
    public void Test922() {
        List<String> list = new ArrayList<>();
        list.add("2020-09-23");
        list.add("2020-09-22");
        list.add("2020-09-21");
        list.add("2020-09-20");

        System.out.println(list);

        List<String> list1 = new ArrayList<>(list);
        System.out.println(list1);
        list1.remove(LocalDate.now().toString());
        System.out.println(list1);
    }

    @Test
    public void Test939() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);

        list.add(0, -1);
        System.out.println(list);
        list.add(0, -2);
        System.out.println(list);


    }

    /**
     * list排序
     */
    @Test
    public void Test955() {
        List<TotalInfo> totalInfos = getTotalInfoList();
        System.out.println(totalInfos);

//        String column = "paySumMoney";
        String column = "arppu";

        totalInfos = totalInfos
                .stream()
                .sorted((o1, o2) -> {
                    return compareByColumn(o1,o2,column);
                }).collect(Collectors.toList());

        System.out.println(totalInfos);

    }

    private List<TotalInfo> getTotalInfoList() {
        List<TotalInfo> totalInfos = new ArrayList<>();
        TotalInfo quchong = new TotalInfo();
        quchong.setGameBaseId(-2);
        quchong.setPaySumMoney(2000L);
        quchong.setArppu("1.12");

        TotalInfo zongshu = new TotalInfo();
        zongshu.setGameBaseId(-1);
        zongshu.setPaySumMoney(1000L);
        zongshu.setArppu("4.52");

        TotalInfo princess = new TotalInfo();
        princess.setGameBaseId(102216);
        princess.setPaySumMoney(200L);
        princess.setArppu("10.99");

        TotalInfo yuanshen = new TotalInfo();
        yuanshen.setGameBaseId(1963);
        yuanshen.setPaySumMoney(500L);
        yuanshen.setArppu("25");

        totalInfos.add(quchong);
        totalInfos.add(zongshu);
        totalInfos.add(princess);
        totalInfos.add(yuanshen);

        return totalInfos;



    }

    private static final String  PAY_SUM_MONEY="paySumMoney";
    private static final String  ACTIVE_ACC_CNT="activeAccCnt";
    private static final String  EFFACTIVE_ACC_CNT="effActiveAccCnt";
    private static final String  ACTIVE_DEVICE_CNT="activeDeviceCnt";
    private static final String  EFF_NEW_ACC_CNT="effNewAccCnt";
    private static final String  EFF_NEW_DECICE_CNT="effNewDeviceCnt";
    private static final String  PAY_ACC_CNT="payAccCnt";
    private static final String  ARPPU="arppu";
    private static final String  TORUIST_CNT="touristCnt";
    private static final String  TOURIST_TRANSFROM_1ST_DAY_CNT="touristTransform1stDayCnt";
    private static final String  MAX_ONLINE_ACC_CNT="maxOnlineAccCnt";
    private static final String  AVG_ONLIE_ACC_CNT="avgOnlineAccCnt";
    private static final String  PAY_BILL_CNT="payBillCnt";

    private int compareByColumn (TotalInfo o1 ,TotalInfo o2, String column){
        int copareValue = 0;
        if(o1.getGameBaseId() == -1 || o1.getGameBaseId() == -2 || o2.getGameBaseId() == -1 || o2.getGameBaseId() == -2){
            return copareValue;
        }
        switch (column) {
            case PAY_SUM_MONEY:
                Long paySumMoney1 = o1.getPaySumMoney() == null ? 0L : o1.getPaySumMoney();
                Long paySumMoney2 = o2.getPaySumMoney() == null ? 0L : o2.getPaySumMoney();
                copareValue =   paySumMoney2.compareTo(paySumMoney1);
                break;
            case EFFACTIVE_ACC_CNT:
                Integer effecttiveAccCnt1 =  o1.getEffActiveAccCnt() ==null ? 0:o1.getEffActiveAccCnt();
                Integer effecttiveAccCnt2 =  o2.getEffActiveAccCnt() ==null ? 0:o2.getEffActiveAccCnt();
                copareValue =   effecttiveAccCnt2.compareTo(effecttiveAccCnt1);
                break;
            case ACTIVE_ACC_CNT:
                Integer getActiveAccCnt1 =  o1.getActiveAccCnt() ==null ? 0:o1.getActiveAccCnt();
                Integer getActiveAccCnt2 =  o2.getActiveAccCnt() ==null ? 0:o2.getActiveAccCnt();
                copareValue =   getActiveAccCnt2.compareTo(getActiveAccCnt1);
                break;
            case ACTIVE_DEVICE_CNT:
                Integer getActiveDeviceCnt1 =  o1.getActiveDeviceCnt() ==null ? 0:o1.getActiveDeviceCnt();
                Integer getActiveDeviceCnt2 =  o2.getActiveDeviceCnt() ==null ? 0:o2.getActiveDeviceCnt();
                copareValue =   getActiveDeviceCnt2.compareTo(getActiveDeviceCnt1);
                break;
            case EFF_NEW_ACC_CNT:
                Integer getEffNewAccCnt1 =  o1.getEffNewAccCnt() ==null ? 0:o1.getEffNewAccCnt();
                Integer getEffNewAccCnt2 =  o2.getEffNewAccCnt() ==null ? 0:o2.getEffNewAccCnt();
                copareValue =   getEffNewAccCnt2.compareTo(getEffNewAccCnt1);
                break;
            case EFF_NEW_DECICE_CNT:
                Integer getEffNewDeviceCnt1 =  o1.getEffNewDeviceCnt() ==null ? 0:o1.getEffNewDeviceCnt();
                Integer getEffNewDeviceCnt2 =  o2.getEffNewDeviceCnt() ==null ? 0:o2.getEffNewDeviceCnt();
                copareValue =   getEffNewDeviceCnt2.compareTo(getEffNewDeviceCnt1);
                break;
            case ARPPU:
                Double getArppu1 =  o1.getArppu() ==null ? 0:Double.valueOf(o1.getArppu());
                Double getArppu2 =  o2.getArppu() ==null ? 0:Double.valueOf(o2.getArppu());
                copareValue =   getArppu2.compareTo(getArppu1);
                break;
            case PAY_ACC_CNT:
                Integer getPayAccCnt1 =  o1.getPayAccCnt() ==null ? 0:o1.getPayAccCnt();
                Integer getPayAccCnt2 =  o2.getPayAccCnt() ==null ? 0:o2.getPayAccCnt();
                copareValue =   getPayAccCnt2.compareTo(getPayAccCnt1);
                break;
            case TORUIST_CNT:
                Integer getTouristCnt1 =  o1.getTouristCnt() ==null ? 0:o1.getTouristCnt();
                Integer getTouristCnt2 =  o2.getTouristCnt() ==null ? 0:o2.getTouristCnt();
                copareValue =   getTouristCnt2.compareTo(getTouristCnt1);
                break;
            case TOURIST_TRANSFROM_1ST_DAY_CNT:
                Integer getTouristTransform1stDayCnt1 =  o1.getTouristTransform1stDayCnt() ==null ? 0:o1.getTouristTransform1stDayCnt();
                Integer getTouristTransform1stDayCnt2 =  o2.getTouristTransform1stDayCnt() ==null ? 0:o2.getTouristTransform1stDayCnt();
                copareValue =   getTouristTransform1stDayCnt2.compareTo(getTouristTransform1stDayCnt1);
                break;
            case MAX_ONLINE_ACC_CNT:
                Integer getMaxOnlineAccCnt1 =  o1.getMaxOnlineAccCnt() ==null ? 0:o1.getMaxOnlineAccCnt();
                Integer getMaxOnlineAccCnt2 =  o2.getMaxOnlineAccCnt() ==null ? 0:o2.getMaxOnlineAccCnt();
                copareValue =   getMaxOnlineAccCnt2.compareTo(getMaxOnlineAccCnt1);
                break;
            case AVG_ONLIE_ACC_CNT:
                Integer getAvgOnlineAccCnt1 =  o1.getAvgOnlineAccCnt() ==null ? 0:o1.getAvgOnlineAccCnt();
                Integer getAvgOnlineAccCnt2 =  o2.getAvgOnlineAccCnt() ==null ? 0:o2.getAvgOnlineAccCnt();
                copareValue =getAvgOnlineAccCnt2.compareTo(getAvgOnlineAccCnt1);
                break;
            case PAY_BILL_CNT:
                Integer getPayBillCnt1 =  o1.getPayBillCnt() ==null ? 0:o1.getPayBillCnt();
                Integer getPayBillCnt2 =  o2.getPayBillCnt() ==null ? 0:o2.getPayBillCnt();
                copareValue =getPayBillCnt2.compareTo(getPayBillCnt1);
                break;
            default:
                Long paySumMoney3 = o1.getPaySumMoney() == null ? 0L : o1.getPaySumMoney();
                Long paySumMoney4 = o2.getPaySumMoney() == null ? 0L : o2.getPaySumMoney();
                return paySumMoney4.compareTo(paySumMoney3);
        }
        return copareValue;
    }

    @Test
    public void Test1103() {
        List<String> dsList = new ArrayList<>();
        dsList.add("2020-09-25");
        dsList.add("2020-09-24");
        dsList.add("2020-09-23");
        dsList.add("2020-09-22");
        dsList.add("2020-09-21");
        dsList.add("2020-09-20");

        List<GameRealTimeData> list = new ArrayList<>();
        GameRealTimeData g1 = new GameRealTimeData();
        g1.setFmtDs("2020-09-25");
        g1.setMaxActiveAccCnt("100");

        GameRealTimeData g2 = new GameRealTimeData();
        g2.setFmtDs("2020-09-21");
        g2.setMaxActiveAccCnt("30");

        list.add(g1);
        list.add(g2);

        System.out.println(list);
        if(dsList.size() > list.size()){
            List<String> dataDsList = list.stream().map(GameRealTimeData::getFmtDs).collect(Collectors.toList());
            dsList.removeAll(dataDsList);
            List<GameRealTimeData> finalList = list;
            dsList.forEach(ds->{
                finalList.add(new GameRealTimeData(ds));
            });
        }
        System.out.println(list);

        list = list.stream().sorted(Comparator.comparing(GameRealTimeData::getFmtDs).reversed()).collect(Collectors.toList());
        System.out.println(list);

    }

    @Test
    public void Test1142() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setTitle("class1");
        s1.setName("w1");
        s1.setGrade(10);

        Student s2 = new Student();
        s2.setTitle("class1");
        s2.setName("w2");
        s2.setGrade(20);

        Student s3 = new Student();
        s3.setTitle("class1");
        s3.setName("w3");
        s3.setGrade(30);

        Student s4 = new Student();
        s4.setTitle("class1");
        s4.setName("w4");
        s4.setGrade(40);

        Student s5 = new Student();
        s5.setTitle("class1");
        s5.setName("w5");
        s5.setGrade(50);

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);

        System.out.println(list);

        Map<String, List<Student>> map = list.stream().collect(Collectors.groupingBy(Student::getTitle));
        System.out.println(map);

    }

    @Test
    public void Test1183() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setTitle("class1");
        s1.setName("w1");
        s1.setGrade(10);

        Student s2 = new Student();
        s2.setTitle("class1");
        s2.setName("w2");
        s2.setGrade(20);

        list.add(s1);
        list.add(s2);
        System.out.println(list);
        list.stream().forEach(e->{
            if(e.getGrade()>15){
                e.setGrade(100);
            }
        });
        System.out.println(list);

    }

    @Test
    public void Test1208() {
        List<Student> list1 = getStudentList();
        System.out.println(list1);

        /**
         * 多个的只会拿第一个
         */
        Optional<Student> max = list1.stream().max(Comparator.comparing(Student::getGrade));
        Optional<Student> min = list1.stream().min(Comparator.comparing(Student::getGrade));
        if(max.isPresent()){
            System.out.println("max: " + max.get());
        }

        if(min.isPresent()){
            System.out.println("min: " + min.get());
        }

        Integer value = list1.stream().mapToInt(Student::getGrade).reduce(0, (acc, x) -> acc + x);
        System.out.println(value);

//        Integer value1 = list1.stream()
        String names = list1.stream().map(Student::getName).collect(Collectors.joining(",","[","]"));
        System.out.println(names);


    }

    @Test
    public void Test1236() {
        List<Student> list = getStudentList();
        System.out.println(list);

        String a = "abc123";
        String b = "123";
        if(a.contains(b)){
            System.out.println("yes");
        }

        List<Student> list1 = list.stream().filter(e->e.getGrade() > 100).collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(list1.size());

    }

    @Test
    public void Test1253() {
        List<Student> list = getStudentList();
        System.out.println(list);

        List<Integer> numList = new ArrayList<>();
        numList.add(1);

        List<Student> newList = list.stream().filter(e->!numList.contains(e.getId())).collect(Collectors.toList());
        System.out.println(newList);

        List<Student> newList1 = list.stream().filter(e->e.getGrade() > 100).collect(Collectors.toList());
        List<Student> newList2 = list.stream().filter(e->e.getGrade() > 100).collect(Collectors.toList());
        System.out.println(newList1);
        newList1.addAll(newList2);
        System.out.println(newList1);

    }

    @Test
    public void Test1273() {
        List<Student> list = new ArrayList<>();
        Student s;
        for (int i = 0; i < 3; i++) {
            s = new Student();
            s.setGrade(i);
            s.setName("wrh"+i);
            list.add(s);
        }
        System.out.println(list);

    }

    @Test
    public void Test1287() {
        Integer[] list = new Integer[3];
        list[0] = 1;
        list[2] = 2;
        System.out.println(Arrays.toString(list));

        int[] list1 = new int[3];
        list1[0] = 1;
        list1[2] = 2;
        System.out.println(Arrays.toString(list1));

    }

    @Test
    public void Test1301() {
        Integer[] list = new Integer[3];
        System.out.println(Arrays.toString(list));
        handleIntegerList(list);
        System.out.println(Arrays.toString(list));

    }

    private void handleIntegerList(Integer[] list) {
        list[0] = 100;
    }

    @Test
    public void Test1315() {
        List<String> list = new ArrayList<>();
        String[] arr = new String[2];
        arr[0] = "1";
        arr[1] = "2";

        CollectionUtils.addAll(list,arr);
        System.out.println(list);

    }

    @Test
    public void Test1327() {
        List<GameDayDataV2> list = new ArrayList<>();
        GameDayDataV2 v = new GameDayDataV2();
        if(Objects.nonNull(v.getPaySumMoney())){
            System.out.println("pay not null");
        }else {
            System.out.println("pay null");
        }

        list.stream().forEach(e->{
            if(Objects.nonNull(e.getPaySumMoney())){
                System.out.println("lambda pay null");
            }
        });

    }

    @Test
    public void Test1346() {
        Student s = new Student();
        System.out.println(s);

    }

    /**
     * 数组去空
     */
    @Test
    public void Test1353() {
        Long[] a = new Long[5];
        a[0] = 1L;
        a[1] = 2L;

        Long[] b = new Long[5];
        b[0] = 1L;
        b[1] = 2L;
        System.out.println(Arrays.toString(a));
        handleNullValue(a);

        System.out.println(Arrays.toString(a));
//        removeNullValueByArray(b);
//        System.out.println(Arrays.toString(b));
    }

    private void handleNullValue(Long[] a) {
        a = removeNullValue(a);
    }

    Long[] removeNullValue(Long[] a){

        List<Long> list= new ArrayList<>(a.length);
        for (Long str : a) {
            list.add(str);
        }

        // 删除空的值
        while (list.remove(null)){};

        /*Long[] list2 = list.toArray(new Long[list.size()]);
        return list2;*/

        return list.toArray(new Long[list.size()]);
    }

    @Test
    public void Test1393() {
        Long[] a = new Long[1];
//        a[0] = 1L;
//        a[1] = 2L;
//        a[3] = 3L;
//        a[6] = 6L;
//        a[8] = 8L;
        System.out.println(Arrays.toString(a));

        Long[] b = removeNullValueSmart(a);
        System.out.println(Arrays.toString(b));
    }

    /** 中间的null值补零，最后的去除, 数组去空*/
    Long[] removeNullValueSmart(Long[] a){

        int length = a.length;
        for (int i = 0; i < length ; i++) {
            if(Objects.isNull(a[i]) && i < (length-1)){
                a[i] = 0L;
            }
        }
        if(Objects.isNull(a[length-1])){
            Long[] b = new Long[length-1];
            /*for (int i = 0; i < length-1; i++) {
                b[i] = a[i];
            }*/
            System.arraycopy(a,0,b,0,length-1);
            return b;
        }
        return a;
    }

    @Test
    public void Test1429() {
        Long[] a = new Long[10];
        a[0] = 1L;
        a[1] = 2L;
        a[3] = 3L;
        a[6] = 6L;
        a[8] = 8L;
        System.out.println(Arrays.toString(a));
        ArrayUtils.removeElement(a,null);
        System.out.println(Arrays.toString(ArrayUtils.removeElement(a,null)));


    }

    void removeNullValueByArray(Long[] a){

        List<Long> list= new ArrayList<>(a.length);
        for (Long str : a) {
            list.add(str);
        }

        // 删除空的值
        while (list.remove(null)){};

        Long[] list2 = list.toArray(new Long[list.size()]);
        a = new Long[list.size()];
        a = list2;
    }

    @Test
    public void Test1398() {
        LinkedList<String> list = new LinkedList<>();
        list.add("w1");
        list.add("w2");
        list.add("w3");
        System.out.println(list);
        list.peek();
        System.out.println(list.peek());
        System.out.println(list.peek());
        list.peek();
        System.out.println(list);
    }

    /**
     * LinkedList的遍历方法
     */
    @Test
    public void Test1415() {
        LinkedList<String> list = new LinkedList<>();
        list.add("w1");
        list.add("w2");
        list.add("w3");

        System.out.println("第1种遍历方法");
        for (int size = list.size(), i = 0; i < size; i++) {
            System.out.println(list.get(i));
        }

        System.out.println("第2种遍历方法");
        for (String str: list) {
            System.out.println(str);
        }

        System.out.println("第3种遍历方法");
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }

    @Test
    public void Test1501() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setId(1);
        list.add(s1);
        s1 = new Student();
        s1.setName("w2");
        s1.setId(2);
        list.add(s1);
        System.out.println(list);
    }

    @Test
    public void Test1515() {
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add("123");
        list.add(1.1f);
        list.add(new Object());
        System.out.println(list);

    }

    @Test
    public void Test1526() {
        List<Student> list = getStudentList();

        System.out.println(list);
            list.stream().forEach(e->{
                if (e.getName().equals("w3")) {
                    e.setName("ww");
                }
            });

        System.out.println(list);



    }

    @Test
    public void Test1537() {
        List<Student> list = new ArrayList<>();
        list.stream().forEach(e->{
            System.out.println(e);
        });

    }

    @Test
    public void Test1546() {
        List list = new ArrayList();
        list.add(1);
        list.add("w1");
        System.out.println("list = " + list);

    }

    @Test
    public void Test1555() {
        List<Integer> list = new ArrayList<>();
        System.out.println("list.getClass().getName() = " + list.getClass().getName());

    }

    @Test
    public void Test1562() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setId(1);
        s1.setGrade(1);
        s1.setMoney(1L);

        Student s2 = new Student();
        s2.setName("w2");
        s2.setId(2);
        s2.setGrade(2);
        s2.setMoney(2L);

        list.add(s1);
        list.add(s2);
        System.out.println(list);
        handleListV2(list);
        System.out.println(list);
    }

    private void handleListV2(List<Student> l) {
        List<Student> list = l;
        for (Student s : list) {
            if (s.getName().equals("w1")) {
                s.setName("w3");
                s.setId(999);
                s.setGrade(9999);
                s.setFlag(false);
            }
        }
    }

    @Test
    public void Test1597() {
        List<Student> list = new ArrayList<>();
        Student s ;
        for (int i = 0; i < 2; i++) {
            s = new Student();
            s.setName("w"+i);
            s.setGrade(i*100);
            list.add(s);
        }
        System.out.println("list = " + list);

    }

    @Test
    public void Test1611() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("w"+i);
        }
        System.out.println("list = " + list);
//        Collections.shuffle(list);
        list = list.subList(0,3);
        System.out.println("list = " + list);

    }

    @Test
    public void Test1624() {
        List<String> list = new ArrayList<>();
        list.add("w1");
        list.add("w2");
        list.add("w3");

        if (list.contains(null)) {
            System.out.println("contain null");
        } else {
            System.out.println("not contain null");
        }
    }

    @Test
    public void Test1638() {
        long[] arr = new long[]{1L,2L,3L,4L,5L};
        List<Long> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        long a = Arrays.stream(arr).skip(1).limit(3).sum();
        System.out.println("list = " + list);
        System.out.println("a = " + a);

    }

    @Test
    public void Test1648() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setGrade(1);
        Student s2 = new Student();
        s2.setName("w2");
        s2.setGrade(2);
        Student s3 = new Student();
        s3.setName("w3");
        s3.setGrade(3);
        list.add(s1);
        list.add(s2);
        list.add(s3);

        System.out.println("list = " + list);
        list.forEach(e->{if(e.getGrade()>2){e.setName("xxx");}});
        System.out.println("list = " + list);
    }

    List<Student> getStudentList1(int count) {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Student s1 = new Student();
            s1.setName("w"+i);
            s1.setGrade(i);
            list.add(s1);
        }

        return list;
    }

    @Test
    public void Test1669() {
        List<Student> list1 = getStudentList1(100000);
        List<Student> list2 = getStudentList1(100000);
        List<Integer> gameId = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            gameId.add(i);
        }
        /*gameId.add(2);
        gameId.add(3);
        gameId.add(4);
        gameId.add(5);*/
        long s1 = System.currentTimeMillis();
        list1 = list1.parallelStream().filter(e->!gameId.contains(e.getGrade())).collect(Collectors.toList());
        long s2 = System.currentTimeMillis();
        list2 = list2.stream().filter(e->!gameId.contains(e.getGrade())).collect(Collectors.toList());
        long s3 = System.currentTimeMillis();
        System.out.println("s2-s1 = " + (s2 - s1));
        System.out.println("s3-s2 = " + (s3 - s2));
        System.out.println("list1.size() = " + list1.size());
        System.out.println("list2.size() = " + list2.size());

    }

    @Test
    public void Test1705() {
        List<Integer> list = Stream.of(1,2,3,4,5,6).collect(Collectors.toList());
        System.out.println("list = " + list);


        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6,7);
//        List<Integer> list1 = Arrays.asList(1,2,3,4,5,6,7);
        System.out.println("list1 = " + list1);

        List<Integer> list2 = Collections.nCopies(3,4);
        System.out.println("list2 = " + list2);

        List<Integer> list3 = Lists.newArrayList(1,2,3,4);
        System.out.println("list3 = " + list3);

        List<Integer> list4 = ImmutableList.of(1,2,3,4,5);
        System.out.println("list4 = " + list4);

        list.removeAll(list3);
        System.out.println("remove  1,2,3,4    list = " + list);
    }

    @Test
    public void Test1730() {
        List<String> dataDsList = new ArrayList<>();
        dataDsList.add("2021-01-01");

        List<String> dsList = new ArrayList<>();
        dsList.add("2021-01-01");
        dsList.add("2021-01-02");
        dsList.add("2021-01-03");

        System.out.println("begin");
        dsList.removeAll(dataDsList);
        System.out.println("end");


    }

    @Test
    public void Test1747() {
        List<String> list = getStringList();
        System.out.println(list);
        list.removeIf(e->e.equals("w1"));
        System.out.println(list);

    }

    /** 根据对象某一个属性进行去重*/
    @Test
    public void Test1756() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("w1");
        Student s2 = new Student();
        s2.setId(2);
        s2.setName("w2");
        Student s3 = new Student();
        s3.setId(3);
        s3.setName("w3");
        Student s4 = new Student();
        s4.setId(4);
        s4.setName("w4");
        Student s5 = new Student();
        s5.setId(1);
        s5.setName("w5");
        Student s6 = new Student();
        s6.setId(3);
        s6.setName("w6");

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);

        List<Student> newList = list.stream()
                .collect(
                        Collectors.collectingAndThen(
                            Collectors.toCollection(
                                ()-> new TreeSet<>(Comparator.comparing(Student::getId))
                            ),ArrayList::new
                        )
                );

        System.out.println("newList = " + newList);

        /** 第二种容易理解的方法*/
        TreeSet<Student> treeSet = new TreeSet<>(Comparator.comparing(Student::getId));
        for (Student s : list) {
            treeSet.add(s);
        }
        List<Student> newList2 = new ArrayList<>(treeSet);
        System.out.println("newList2 = " + newList2);

    }

    @Test
    public void Test1807() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("w1");

        Student s2 = new Student();
        s2.setId(1);
        s2.setName("w1");

        list.add(s1);
        list.add(s2);

        System.out.println("s1.hashCode() = " + s1.hashCode());
        System.out.println("s2.hashCode() = " + s2.hashCode());

        System.out.println("list.indexOf(s1) = " + list.indexOf(s1));
        System.out.println("list.indexOf(s2) = " + list.indexOf(s2));

    }

    @Test
    public void Test1829() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("w1");

        Student s2 = new Student();
        s2.setId(1);
        s2.setName("w1");

        list.add(s1);
        list.add(s2);

        System.out.println("list = " + list);
        /** list去重*/
        list = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(Student::getName))), ArrayList::new));
        System.out.println("list = " + list);

    }

    @Test
    public void Test1850() {
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("w1");
        Map<String, String> map = new HashMap<>();
        map.put("name", s1.getName());
        System.out.println("map = " + map);

        s1 = new Student();
        s1.setName("w2");
        System.out.println("map = " + map);

    }

    @Test
    public void Test1865() {
        List<Student> list = new ArrayList<>();
        Map<String, Integer> payMap = list.stream().collect(Collectors.toMap(Student::getName, Student::getId));
        System.out.println("payMap = " + payMap);


    }

    @Test
    public void Test1874() {
        List<Student> list = getStudentList();
        System.out.println("list = " + list);
        Map<String, Integer> map = new HashMap<>();
        map.put("w1", 100);
        map.put("w3", 300);
        list.forEach(e->{
            if (map.containsKey(e.getName())) {
                e.setGrade(map.get(e.getName()));
            }
        });
        System.out.println("list = " + list);

    }

    @Test
    public void Test1888() {
        List<Student> list = getStudentList();
        System.out.println("list = " + list);
        list.stream().peek(e->{
            System.out.println("e = " + e);
            e.setName("www");
        });
        System.out.println("list = " + list);


    }

    @Test
    public void Test1901() {
        List<Student> list = getStudentList();
        System.out.println("list = " + list);
        list = list.stream().filter(e->e.getGrade() > 1).collect(Collectors.toList());
        System.out.println("list = " + list);

    }

    /** list排序*/
    @Test
    public void Test1910() {
        List<Student> list = getStudentList();
        System.out.println("list = " + list);
        list.sort(Comparator.comparing(Student::getName));
        System.out.println("list = " + list);

        list.sort(Student::compareByNameThenGrade);
        System.out.println("list = " + list);

        /** 这个和上面是同样的计算逻辑*/
        list.sort(Comparator.comparing(Student::getName).thenComparing(Student::getGrade));
        System.out.println("list = " + list);

    }

    /**
     * 前面的例子中都是有值元素排序，能够覆盖大部分场景，但有时候我们还是会碰到元素中存在null的情况：
     *
     * 列表中的元素是 null
     * 列表中的元素参与排序条件的字段是 null
     *
     * 使用Comparator.nullsLast和Comparator.nullsFirst
     * Comparator.nullsLast实现null在结尾 Comparator.nullsFirst实现null在开头
     *
     * 代码 1 是第一层 null-safe 逻辑，用于判断元素是否为 null；
     * 代码 2 是第二层 null-safe 逻辑，用于判断元素的条件字段是否为 null；
     * 代码 3 是条件Comparator，这里使用了Comparator.naturalOrder()，是因为使用了String排序，也可以写为String::compareTo。
     * 如果是复杂判断，可以定义一个更加复杂的Comparator，组合模式就是这么好用，一层不够再套一层。
     */
    @Test
    public void Test1927() {
        List<Student> list = getStudentList();
        final Comparator<Student> nullsLast = Comparator.nullsLast(
                Comparator.nullsLast( // 1
                        Comparator.comparing(
                                Student::getName,
                                Comparator.nullsLast( // 2
                                        Comparator.naturalOrder() // 3
                                )
                        )
                )
        );

        list.sort(nullsLast);
    }

    @Test
    public void Test1958() {
        List<DataList> list = new ArrayList<>();
        List<String> name = new ArrayList<>();
        name.add("w1");
        name.add("w2");
        name.add("w3");

        AtomicInteger index = new AtomicInteger();
        name.stream().forEach(e->{
            list.add(new DataList(e,index.getAndIncrement()));
        });

        System.out.println("list = " + list);

    }

    @Test
    public void Test1977() {
       List<Student> l = new ArrayList<>();
        List<Student> collect = l.stream().filter(a -> a != null).map(x -> {
            Student s = new Student();
            return s;
        }).collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }

    @Test
    public void Test1987() {
        List list = Arrays.asList(1,2,3);
        System.out.println("list.size() = " + list.size());

        /** 数组接收则为1*/
        int[] a = {1,2,3};
        list = Arrays.asList(a);
        System.out.println("list.size() = " + list.size());

        /** 解决方法1*/
        list = Arrays.stream(a).boxed().collect(Collectors.toList());
        System.out.println("list.size() = " + list.size());

        /** 解决方法2 使用包装类型*/
        Integer[] b = {1,2,3};
        list = Arrays.asList(b);
        System.out.println("list.size() = " + list.size());
        b[0] = 0;
        System.out.println("list = " + list);

    }

    @Test
    public void Test2010() {
        List<String> l1 = new ArrayList<>();
        l1.add("1,2,3");
        l1.add("4,5,6");
        l1.add("7,8,9");

        System.out.println("l1 = " + l1);
    }

    @Test
    public void Test2020() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setId(1);
        Student s2 = new Student();
        s2.setName("w2");
        s2.setId(2);
        Student s3 = new Student();
        s3.setName("w3");
        s3.setId(3);
        list.add(s1);
        list.add(s2);
        list.add(s3);
        System.out.println("list = " + list);

        Integer maxPort = 10005;
        AtomicInteger insertBeginPort = Objects.isNull(maxPort) ? new AtomicInteger(10000) : new AtomicInteger(maxPort + 1);
        list.stream().forEach(e -> {
            e.setMoney(Long.valueOf(insertBeginPort.getAndIncrement()));
        });

        System.out.println("list = " + list);

    }

    @Test
    public void Test2048() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setId(1);
        s1.setTime(LocalDateTime.now());
        Student s2 = new Student();
        s2.setName("w2");
        s2.setId(2);
        s2.setTime(LocalDateTime.now().plusHours(1L));
        Student s3 = new Student();
        s3.setName("w3");
        s3.setId(3);
        s3.setTime(LocalDateTime.now().plusHours(2L));
        list.add(s1);
        list.add(s2);
        list.add(s3);
        System.out.println("list = " + list);
        System.out.println("JSON.toJSONString(list) = " + JSON.toJSONString(list));
        System.out.println("JSON.toJSONStringWithDateFormat(list, \"yyyy-MM-dd HH:mm:ss\") = " + JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss"));


    }

    @Test
    public void Test2074() {
        List<Student> list = new ArrayList<>();
                Student s1 = new Student();
                s1.setName("w1");
                s1.setId(1);
                Student s2 = new Student();
                s2.setName("w2");
                s2.setId(2);
                Student s3 = new Student();
                s3.setName("w3");
                s3.setId(3);
                list.add(s1);
                list.add(s2);
                list.add(s3);
                System.out.println("list = " + list);

                list.stream().forEach(e->{
                    e.setName("change1");
                });
        System.out.println("list = " + list);

    }

    @Test
    public void Test2098() {
        List<String> list = new ArrayList<>();
        list.add("2,2,1\\u0000\\u0000\\u0000");
        System.out.println("list = " + list);

        list = list.stream().map(e->e.replace("\\u0000","")).collect(Collectors.toList());
        System.out.println("list = " + list);

        String q1 = "2,2,1\u0000\u0000\u0000";
        List<String> queryResult = new ArrayList<>();
        queryResult.add(q1);
        queryResult = queryResult.stream().map(e->e.replace("\u0000","")).collect(Collectors.toList());

    }

    @Test
    public void Test2114() {
        Integer maxPort = 10000;
        AtomicInteger insertBeginPort = Objects.isNull(maxPort) ? new AtomicInteger(10000) : new AtomicInteger(maxPort + 1);
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setId(1);
        Student s2 = new Student();
        s2.setName("w2");
        s2.setId(2);
        Student s3 = new Student();
        s3.setName("w3");
        s3.setId(3);
        list.add(s1);
        list.add(s2);
        list.add(s3);
        System.out.println("list = " + list);
        list.stream().forEach(e -> {
            e.setGrade(insertBeginPort.getAndIncrement());
        });
        System.out.println(list);

    }

    @Test
    public void Test2139() {
        List<String> columnIn = new ArrayList<String>(){{
            add("id");
        }};
        List<String> columnOut = new ArrayList<String>(){{
            add("id");
        }};
        List<String> oriColumnIn = new ArrayList<>(columnIn);

        columnIn.addAll(columnOut);
//        columnIn = columnIn.stream().distinct().collect(Collectors.toList());

        boolean allColumnFlag = false;
        if (org.apache.commons.collections4.CollectionUtils.isEqualCollection(oriColumnIn, columnIn)) {
            allColumnFlag = true;
        }

        System.out.println("columnIn = " + columnIn);
        System.out.println("oriColumnIn = " + oriColumnIn);

        System.out.println("allColumnFlag = " + allColumnFlag);

    }

    @Test
    public void Test2161() {
        List<Student> studentList = getStudentList();
        System.out.println("studentList = " + studentList);

        studentList.stream().map(e->{
            e.setName(e.getName() + " new");
            return e;
        }).collect(Collectors.toList());

        System.out.println("studentList = " + studentList);

    }

}