package com.wrh.thread.threeCreateMethods;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.util.StopWatch;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:05 2018/8/28 0028
 * @Modified By:
 */
@Slf4j
public class TestThreeCreatThreadMethod {

    static class Thread1 extends Thread{
        @Override
        public void run(){
            System.out.println("thread1 run...");
        }
    }

    static class Thread2 implements Runnable{

        @Override
        public void run() {
            System.out.println("thread2 run...");
        }
    }

    static class Thread3 implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("thread3 run...");
            return "java";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread t1 = new Thread1();

        Thread t2 = new Thread(new Thread2());

        FutureTask<String> ft = new FutureTask<String>(new Thread3());
        Thread t3 = new Thread(ft);

        t1.start();
        t2.start();
        t3.start();

        System.out.println(ft.get());
        stopWatch.stop();
        log.info("===>  Time: {}", stopWatch.prettyPrint());
    }
}
