package com.wrh.collection.sort;

import com.google.common.collect.Comparators;
import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:18 2019/7/18 0018
 * @Modified By:
 */
public class TestSort {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        System.out.println(isSorted(list,list.size()));
        System.out.println(isSortedGuava1(list));
    }

    public static boolean isSorted(List<String> listOfStrings, int index){
        if(index < 2){
            return true;
        }else if(listOfStrings.get(index - 2).compareTo(listOfStrings.get(index - 1)) > 0){
            return false;
        }else {
            return isSorted(listOfStrings,index - 1);
        }
    }

    public static boolean isSortedGuava1(List<String> listOfStrings){
        return Ordering.<String> natural().isOrdered(listOfStrings);
    }

    public static boolean isSortedGuava2(List<String> listOfStrings, Comparator<String> stringComparator){
        return Ordering.from(stringComparator).isOrdered(listOfStrings);
    }
}
