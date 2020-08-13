package com.wrh.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:50 2018/8/28 0028
 * @Modified By:
 */
public class TestArrayList {

    private static List<Integer> list = new ArrayList<>();
    private static ConcurrentHashMap<String , String> concurrentHashMap = new ConcurrentHashMap<>();
    private static Map<String, String> map = new HashMap<>();


    public static void main(String[] args) throws InterruptedException {
        for( int i = 0; i< 10; i++){
            testList();
//            list.clear();
//            concurrentHashMap.clear();
            map.clear();
        }
    }

    private static void testList() throws InterruptedException {

        /*Runnable runnable = () ->{

            for( int i = 0; i< 10000; i++){
                list.add(i);
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);*/

        /*t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        *//**
         * 结果充分说明线程不安全
         *//*
        System.out.println("list.size():{}"+ list.size());*/


/*        Runnable runnable1 = () ->{

            System.out.println("current thread name:" + Thread.currentThread().getName());
            for( int i = 0; i< 10000; i++){
                concurrentHashMap.put(Thread.currentThread().getName() + i ,String.valueOf(i));
            }
        };

        Thread t4 = new Thread(runnable1);
        Thread t5 = new Thread(runnable1);
        Thread t6 = new Thread(runnable1);

        t4.start();
        t5.start();
        t6.start();

        t4.join();
        t5.join();
        t6.join();


        System.out.println(concurrentHashMap.size());*/

        Runnable runnable2 = () ->{

            System.out.println("current thread name:" + Thread.currentThread().getName());
            for( int i = 0; i< 10000; i++){
                map.put(Thread.currentThread().getName() + i ,String.valueOf(i));
            }
        };

        Thread t4 = new Thread(runnable2);
        Thread t5 = new Thread(runnable2);
        Thread t6 = new Thread(runnable2);

        t4.start();
        t5.start();
        t6.start();

        t4.join();
        t5.join();
        t6.join();


        System.out.println(map.size());



    }

    @Test
    public void test$1() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        System.out.println(list);
        list.add(null);
        System.out.println(list);
    }
}
