package com.wrh.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:34 2019/6/6 0006
 * @Modified By:
 */
@Slf4j
public class TestNumber {
    @Test
    public void test(){
        Integer i = 300;
        Integer j = 300;

        if( i == j ){
            log.info("==");
        }else {
            log.info("!=");
        }

        int k = 300;
        if( i == k){
            log.info("i == k");
        }else {
            log.info("i != k");
        }
    }

    @Test
    public void test1(){
        Integer i1 = 127;
        Integer i2 = 127;

        Integer i3 = 300;
        Integer i4 = 300;

        if(i1 == i2){
            log.info("i1==i2");
        }

        if(i3 == i4){
            log.info("i3==i4");
        }

    }

    /**
     * 可变参数
     */
    @Test
    public void test2(){
        printMore("1","2","3");
        log.info("================================================");
        printMore("1","2","3","4");
    }

    void printMore(String... strings){
        for (int i = 0; i < strings.length; i++) {
            log.info("===> {}",strings[i]);
        }
    }

    @Test
    public void Test68() {
        int i = 1;
        i = ++i;
        System.out.println(i);
        int j = i++;
        System.out.println(j);
        int k = i + ++i * i++;
        System.out.println(k);

    }

    @Test
    public void Test80() {
        int a = 1;
        int b = 5;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("=================================");

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a = " + a);
        System.out.println("b = " + b);

    }
}
