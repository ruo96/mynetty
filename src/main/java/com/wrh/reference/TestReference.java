package com.wrh.reference;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:05 2018/10/30 0030
 * @Modified By:
 */
@Slf4j
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

    @Test
    public void test(){
        int i =2;
        changeTheNumber(i);
        log.info(" i is :{}",i);

        Integer i1 =1111;
        changeTheNumber(i1);
        log.info(" i is :{}",i1);
    }

    private void changeTheNumber(Integer i) {
        i = 6;
    }
}
