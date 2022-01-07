package com.wrh.collection.set;

import com.alibaba.fastjson.JSON;
import com.wrh.elasticsearch.Student;
import io.netty.util.internal.ConcurrentSet;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:50 2019/4/29 0029
 * @Modified By:
 */
public class TestSet {
    public static void main(String[] args) {
        Set<String> largeBundleNosSet = new HashSet<>();

        largeBundleNosSet.add("123");
        largeBundleNosSet.add("123");
        largeBundleNosSet.add("123");
        largeBundleNosSet.add(null);

        System.out.println(largeBundleNosSet.size());

        Students students1 = new Students();
        /*students1.setId(1);
        students1.setName("张三");*/
        Students students2 = new Students();
        /*students2.setId(2);
        students2.setName("李四");*/
        Set<Students> set = new ConcurrentSet<>();
        set.add(students1);
        set.add(students2);
        System.out.println(" set size:  "+set.size());


        Set<Students> set2 = Collections.newSetFromMap(new ConcurrentHashMap<>());
        System.out.println("before add new object: size IS : " + set2.size());
        Students s1 = new Students();
        set2.add(s1);
        System.out.println("after add new object: size IS : " + set2.size());
        Students s2 = new Students();
        set2.add(s2);
        System.out.println("final add new object: size IS : " + set2.size());
        set2.clear();
        System.out.println("after clear: size IS : " + set2.size());


    }

    @Test
    public void Test55() {
        Students s1 = new Students();
        s1.setName("w1");
        s1.setId(2);

        Students s2 = new Students();
        s2.setName("w2");
        s2.setId(1);

        Students s3 = new Students();
        s3.setName("w3");
        s3.setId(3);

        TreeSet<Students> t = new TreeSet<>();
        t.add(s1);
        t.add(s2);
        t.add(s3);

        System.out.println(t);

    }

    @Test
    public void Test80() {
        Set<String> s1 = new HashSet<>();
        s1.add("w2");
        s1.add("w1");

        Set<String> s2 = new HashSet<>();
        s2.add("w1");
        s2.add("w2");

        System.out.println(s1);
        System.out.println(s2);

        if(s1.equals(s2)){
            System.out.println("equal");
        }

    }

    /**
     * list去重
     */
    @Test
    public void Test99() {
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));

        System.out.println(numbersList);

        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(numbersList);
        Set<Integer> hashSet1 = new HashSet<>(numbersList);

        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(hashSet);
        ArrayList<Integer> listWithoutDuplicates1 = new ArrayList<>(hashSet1);

        System.out.println(listWithoutDuplicates);
        System.out.println(listWithoutDuplicates1);

        List<Integer> list = numbersList.stream().distinct().collect(Collectors.toList());
        System.out.println("list = " + list);



    }

    @Test
    public void Test122() {
        Set<Integer> s = new HashSet<>();
        System.out.println("s.add(1) = " + s.add(1));
        System.out.println("s.add(1) = " + s.add(1));
        System.out.println("s.add(2) = " + s.add(2));
        System.out.println("s.add(2) = " + s.add(2));


    }

    @Test
    public void Test132() {
        Set<String> set = new HashSet<>();
        Student s1 = new Student();
        s1.setName("w1");
        s1.setId(0);
        s1.setGrade(0);
        s1.setMoney(0L);
        s1.setTitle("");
        s1.setFlag(false);

        Student s2 = new Student();
        s2.setName("w2");
        s2.setId(0);
        s2.setGrade(0);
        s2.setMoney(0L);
        s2.setTitle("");
        s2.setFlag(false);

        set.add(JSON.toJSONString(s1));
        set.add(JSON.toJSONString(s2));
        System.out.println("set = " + set);

        String setStr = JSON.toJSONString(set);
        System.out.println("setStr = " + setStr);


        List<Student> list = new ArrayList<>();
        Student s3 = new Student();
        s3.setName("w3");
        s3.setId(0);
        s3.setGrade(0);
        s3.setMoney(0L);
        s3.setTitle("");
        s3.setFlag(false);

        Student s4 = new Student();
        s4.setName("w3");
        s4.setId(0);
        s4.setGrade(0);
        s4.setMoney(0L);
        s4.setTitle("");
        s4.setFlag(false);

        list.add(s3);
        list.add(s4);

        String listStr = JSON.toJSONString(list);

        List<Student> list111 = JSON.parseArray(listStr, Student.class);
        Set<Student> set1 = new HashSet<>(list111);
        System.out.println("set1 = " + set1);


    }

    @Test
    public void Test189() {
        List<RuleInfo> list = new ArrayList<>();
        RuleInfo r1 = new RuleInfo();
        r1.setName("w2");
        r1.setAge(0);

        RuleInfo r2 = new RuleInfo();
        r2.setName("w1");
        r2.setAge(0);

        list.add(r1);
        list.add(r2);

        //String listStr = JSON.toJSONString(list);
        String listStr = JSON.toJSONString(JSON.toJSONString(r1));

        List<RuleInfo> list111 = JSON.parseArray(listStr, RuleInfo.class);
        Set<RuleInfo> set1 = new HashSet<>(list111);
        System.out.println("set1 = " + set1);

        RuleInfo r3 = new RuleInfo();
        r3.setName("w1");
        r3.setAge(1);

        set1.add(r3);
        System.out.println("set1 = " + set1);


    }
}
