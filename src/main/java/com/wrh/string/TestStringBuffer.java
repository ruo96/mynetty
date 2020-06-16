package com.wrh.string;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:49 2019/7/15 0015
 * @Modified By:
 */
public class TestStringBuffer {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        sb.append("123").append("-").append("456");
        System.out.println(sb.toString());
        boolean loopFlag = true;
        int i = 0;
        while(loopFlag){
            System.out.println("-------" + ( i++));
            if(i > 10){
                loopFlag = false;
            }
        }

        Map<Integer, Set<Integer>> map = new HashMap<>();
        if(null != map.get(1)){
            map.get(1).add(2);
        }else {
            map.put(1,new HashSet<>());
        }

        System.out.println(map.get(1));
        map.get(1).add(2);
        System.out.println(map.get(1));

        System.out.println(1+2+"-"+3);


    }

    @Test
    public void Test1() {
        /**
         * 有synchronized修饰
         */
        StringBuffer sb = new StringBuffer();
        sb.append("123");

        StringBuilder sb1 = new StringBuilder();
        sb1.append("123");
    }
}
