package com.wrh.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:05 2019/1/9 0009
 * @Modified By:
 */
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
}