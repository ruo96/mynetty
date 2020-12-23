package com.wrh.thread;

import com.wrh.thread.threadPool.task.MyRunnable;
import org.junit.Test;

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
                TimeUnit.SECONDS.sleep(5);
                System.out.println("###");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        /*Thread thread1 = new Thread(()->{
            *//*try {
                System.out.println("***");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*//*
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(true){

                System.out.println("****");
            }


        });*/
//        thread.setDaemon(true);
        thread.start();
//        thread1.start();
        System.out.println("main start sleep");
//        TimeUnit.SECONDS.sleep(5);
//        System.out.println("main sleep done!");
//        thread.join();
        System.out.println("main done");
        System.exit(0);


    }

    public class MyRunable123 implements  Runnable{

        private int i=0;
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("+++:  " +  Thread.currentThread().getName());
        }
    }

    @Test
    public void Test101() {
        Thread t = new Thread(new MyRunable123());
        System.out.println("begin");
        t.start();
//            TimeUnit.SECONDS.sleep(1);
            System.out.println("###" + Thread.currentThread().getName());
//        } /*catch (InterruptedException e) {
//            e.printStackTrace();
//        }*/
        System.out.println("end");

    }

    @Test
    public void Test123() {
        Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("###" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        System.out.println("main start sleep");
        System.out.println("main done");

    }
}
