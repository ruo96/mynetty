package com.wrh.list;

import com.alibaba.fastjson.JSON;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.wrh.elasticsearch.Student;
import com.wrh.list.vo.GameConfig;
import com.wrh.list.vo.TotalYearKpi;
import com.wrh.utils.GsonUtils;
import com.wrh.utils.test.Dog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

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

    public List<String> getStringList(){
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

    private List<Student> getStudentList() {
        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setId(1);
        s1.setGrade(1);

        Student s2 = new Student();
        s2.setName("w1");
        s2.setId(1);
        s2.setGrade(2);

        Student s3 = new Student();
        s3.setName("w2");
        s3.setId(2);
        s3.setGrade(2);

        Student s4 = new Student();
        s4.setName("w2");
        s4.setId(2);
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
        long m1 = Runtime.getRuntime().freeMemory()/1024;
        System.out.println("start memory: " + m1 + "kb");
        for(Integer i1 : list){
            int[] i = new int[1024*1024];
            for (int j = 0; j < 1024*1024; j++) {
                i[j] = j;
            }
            long m2 = Runtime.getRuntime().freeMemory()/1024;
            System.out.println("this time memory: " + m2 + "kb");
            System.out.println(m1 - m2);
        }
        long m2 = Runtime.getRuntime().freeMemory()/1024;
        System.out.println("finally");
        System.out.println(m1 - m2);

        TimeUnit.SECONDS.sleep(5);

        m2 = Runtime.getRuntime().freeMemory()/1024;
        System.out.println(" real finally");
        System.out.println(m1 - m2);

        System.gc();
        m2 = Runtime.getRuntime().freeMemory()/1024;
        System.out.println(" gc finally");
        System.out.println(m1 - m2);
    }




}