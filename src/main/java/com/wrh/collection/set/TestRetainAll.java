package com.wrh.collection.set;

import java.util.HashSet;
import java.util.Set;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 12:33 2019/6/23 0023
 * @Modified By:
 */
public class TestRetainAll {
    public static void main(String[] args) {
        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        Set<String> s3 = new HashSet<>();


        s1.add("1");
        s1.add("2");
        s1.add("3");

        s2.add("3");
        s2.add("4");
        s2.add("5");

        s3 = new HashSet<>(s2);

        s2.retainAll(s1);

        System.out.println(s2);
        System.out.println(s2.size());
        System.out.println(s3);

    }
}
