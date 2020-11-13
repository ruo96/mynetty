package com.wrh.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuruohong
 * @Classname TestLong
 * @Description TODO
 * @Date 2020/11/9 10:57
 */
@Slf4j
public class TestLong {
    @Test
    public void Test14() {
        Long a = -1L ;

        if(a != -1){
            System.out.println("a not equal");
        }else {
            System.out.println("a equal");
        }

        if(a.equals(-1)){
            System.out.println(" a is equal");
        }else {
            System.out.println(" a is not equal");
        }

    }

    @Test
    public void Test33() {
        Map<Integer, Long> o1 = new HashMap<>();
        Map<Integer, Long> o2 = new HashMap<>();
        o1.put(1,100L);
        o2.put(1,200L);

        System.out.println(o2.get(1) - o1.get(1));
        System.out.println(o2.get(1).compareTo(o1.get(1)));

    }
}
