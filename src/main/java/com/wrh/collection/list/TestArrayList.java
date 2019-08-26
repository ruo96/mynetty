package com.wrh.collection.list;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:43 2019/5/7 0007
 * @Modified By:
 */
public class TestArrayList {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>(10);
        System.out.println("real size: "+a.size());
        a.add("1");
        System.out.println("real size: "+a.size());


        List<String> b = new ArrayList<>(10);
        b.add("2");
        b.add("3");
        b.add("4");

        a.addAll(b);
        System.out.println("after size: " + a);
        System.out.println("after size string: " + JSON.toJSONString(a));

        long c = 10000;
        Integer d = Math.toIntExact(c);
        AtomicInteger e = new AtomicInteger(1);
        System.out.println(e.incrementAndGet());


        List<String> f = new ArrayList<>(10);
        f.add("1");
        f.add("2");
        f.add("3");
        f.add("4");
        f.add("5");
        f.add("6");
        List<String> f1 = Lists.newArrayList(f.subList(0,3));
        System.out.println("f1 is : "+ JSON.toJSONString(f1));

        List<String> g = new ArrayList<>(10);
        g.add("7");
        g.add("8");
        g.add("9");
//        clearList(g);

        f.addAll(g);
        System.out.println("addall f is : "+ JSON.toJSONString(f));

    }

    private static void clearList(List<String> list){
        list.clear();
    }
}
