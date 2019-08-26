package com.wrh.collection.list.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:27 2019/5/21 0021
 * @Modified By:
 */
public class TestSubList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("size:" + list.size());

        List<String> a = list.subList(0,10);
        System.out.println(" a size:  " + a.size());
        System.out.println(a);
    }
}
