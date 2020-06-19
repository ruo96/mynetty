package com.wrh.thread.basicUse;

import clojure.lang.IFn;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wuruohong
 * @Classname TestBasic
 * @Description TODO
 * @Date 2020/6/18 20:02
 */
@Slf4j
public class TestBasic {

    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
    @Test
    public void Test14() throws InterruptedException {
        Thread t1 = new Thread(new QueueThread(queue));
        Thread t2 = new Thread(new QueueThread(queue));
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(queue.size());


    }
}
