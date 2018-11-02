package com.wrh.reference;

import java.util.ArrayList;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:05 2018/10/30 0030
 * @Modified By:
 */
public class TestReference {
    public static void main(String[] args) {

        Integer total = 0;
        paramCheck(total);
        System.out.println("total is : " + total);

        ArrayList<String> arr1 = new ArrayList<>();
        arr1.add("123");
        arr1.add("456");
        addArr(arr1);
        System.out.println("arr1 size is : "+ arr1.size());
        System.out.println("arr1  is : "+ arr1);
    }

    private static void addArr(ArrayList<String> arr1) {
        arr1.add("789");
    }

    private static void paramCheck(Integer total) {
        if(total < 1){
            total +=1;
        }
    }
}
