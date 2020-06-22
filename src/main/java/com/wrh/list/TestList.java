package com.wrh.list;

import com.alibaba.fastjson.JSON;
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


}