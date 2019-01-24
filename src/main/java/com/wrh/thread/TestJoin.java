package com.wrh.thread;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:41 2019/1/21 0021
 * @Modified By:
 */
public class TestJoin {
    /*public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = IntStream.range(1,4).mapToObj(TestJoin::create).collect(toList());

        *//*启动线程, 每一个都启动起来了*//*
        threads.forEach(Thread::start);

        for(Thread thread:threads){
            thread.join(); //这个代表的意思是都插入到主线程里面
        }

        for( int i =0;i<10;i++){
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }


    }

    private static Thread create(int seq){
        return new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName() +"##"+i);
                shortSleep();
            }
        },String.valueOf(seq));
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            try {
                System.out.println("###");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("main sleep done!");
        thread.join();
        System.out.println("main done");


    }
}
