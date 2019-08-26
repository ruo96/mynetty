package com.wrh.collection.set;

import io.netty.util.internal.ConcurrentSet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

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
}
