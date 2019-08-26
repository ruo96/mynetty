package com.wrh.collection.list;

import io.netty.util.internal.ConcurrentSet;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:21 2019/5/7 0007
 * @Modified By:
 */
public class TestThreadArrayListAdd {
    public static void main(String[] args) throws InterruptedException {

        LocalDateTime time1 = LocalDateTime.now();

        List<Integer> a1 = new ArrayList<>();
        List<Integer> a = Collections.synchronizedList(a1);

        Set<Integer> aset = new ConcurrentSet<>();


//        Set<Integer> aset = Collections.newSetFromMap(new ConcurrentHashMap<>());
//        Set<Integer> aset = new ConcurrentSkipListSet<>();


        Runnable runnable1 = () ->{

            System.out.println("current thread name:" + Thread.currentThread().getName());
            for( int i = 0; i< 1000000; i++){
//                a.add(i);
                aset.add(i);
            }
        };

        Runnable runnable2 = () ->{

            System.out.println("current thread name:" + Thread.currentThread().getName());
            for( int i = 1000000; i< 2000000; i++){
//                a.add(i);
                aset.add(i);
            }
        };
        Runnable runnable3 = () ->{

            System.out.println("current thread name:" + Thread.currentThread().getName());
            for( int i = 2000000; i< 3000000; i++){
//                a.add(i);
                aset.add(i);
            }
        };
        Runnable runnable4 = () ->{

            System.out.println("current thread name:" + Thread.currentThread().getName());
            for( int i = 3000000; i< 4000000; i++){
//                a.add(i);
                aset.add(i);
            }
        };
        Runnable runnable5 = () ->{

            System.out.println("current thread name:" + Thread.currentThread().getName());
            for( int i = 4000000; i< 5000000; i++){
//                a.add(i);
                aset.add(i);
            }
        };
        Runnable runnable6 = () ->{

            System.out.println("current thread name:" + Thread.currentThread().getName());
            for( int i = 5000000; i< 6000000; i++){
//                a.add(i);
                aset.add(i);
            }
        };

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        Thread t3 = new Thread(runnable3);
        Thread t4 = new Thread(runnable4);
        Thread t5 = new Thread(runnable5);
        Thread t6 = new Thread(runnable6);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();

        System.out.println("list size: " + a.size());
        System.out.println("list size: " + aset.size());


        LocalDateTime time2 = LocalDateTime.now();
        Duration duration = Duration.between(time1,time2);

        System.out.println("耗费时间： " + duration.getNano());




    }
}
