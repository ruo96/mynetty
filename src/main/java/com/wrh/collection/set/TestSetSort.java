package com.wrh.collection.set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:19 2019/6/14 0014
 * @Modified By:
 */
public class TestSetSort {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("c");
        set.add("b");
        for(String c: set){
            System.out.println(c);
        }
        System.out.println(set);

        final TreeSet ts = new TreeSet(set);

        ts.comparator();
        System.out.println(ts);
    }
}
