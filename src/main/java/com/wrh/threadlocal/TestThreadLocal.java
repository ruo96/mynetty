package com.wrh.threadlocal;

import org.junit.Test;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:05 2018/10/15 0015
 * @Modified By:
 */
public class TestThreadLocal {

    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {

        int i = 16;
        int j = 49 & (i-1);
        System.out.println("j is : " + j);

        AtomicInteger ai = new AtomicInteger(2);

        for(int k=0 ; k<5; k++){

            int andIncrement = (int) ai.getAndAdd(1);
            System.out.println(" andIncrement is : "+ andIncrement);
        }

        Aware aware = new Aware() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };

        BeanPostProcessor beanPostProcessor;

//        ApplicationContextAware;

//        ApplicationContextAwareProcessor applicationContextAwareProcessor
    }

    @Test
    public void test(){
       ThreadLocal<String> threadLocal = new ThreadLocal<>();
       threadLocal.set("wrh");
        System.out.println(threadLocal.get());
    }

    @Test
    public void Test55() {



    }

}
