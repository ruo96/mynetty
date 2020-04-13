package com.wrh.collection.list;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wuruohong
 * @Classname TestLinkedList
 * @Description TODO
 * @Date 2020/3/25 19:25
 */
@Slf4j
public class TestLinkedList {

    @Test
    public void Test() throws InterruptedException {
        List<Integer>  list = new LinkedList<>();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000000; i++) {
                    list.add(i);
                }
            }
        };
        Thread t1 = new Thread(r1);

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 10000001; i < 20000000; i++) {
                    list.add(i);
                }
            }
        };
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        while(true) {
            System.out.println(list.size());
            TimeUnit.SECONDS.sleep(1);
        }



    }

    @Test
    public void Test1() throws InterruptedException {
        LinkedBlockingQueue<Integer> list = new LinkedBlockingQueue<>();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                List<Integer> list1 = new ArrayList<>();
                for (int i = 0; i < 1000; i++) {
                    list1.add(i);
                    if(list1.size() > 200 || i==999) {
                        list.addAll(list1);
                        list1.clear();
                    }

                }
            }
        };
        Thread t1 = new Thread(r1);

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                List<Integer> list1 = new ArrayList<>();
                for (int i = 1000; i < 2000; i++) {
                    list1.add(i);
                    if(list1.size() > 200 || i == 1999) {
                        list.addAll(list1);
                        list1.clear();
                    }
                }
            }
        };
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        while(true) {
            System.out.println(list.size());
            TimeUnit.SECONDS.sleep(1);
        }
    }

    @Test
    public void Test3() {
        Integer a = null;
        System.out.println(a.toString());
    }

    @Test
    public void Test2() throws InterruptedException {
        LinkedBlockingQueue<Integer> list = new LinkedBlockingQueue<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        for(Integer i: list) {
            System.out.println(i);
        }

        list.clear();
        System.out.println(list.size());

    }
}
