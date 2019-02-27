package com.wrh.thread.CountDownLatchTest;

import java.util.concurrent.CountDownLatch;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:47 2019/2/19 0019
 * @Modified By:
 */
public class CountDownLatchTest {

    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
                System.out.println(2);
                c.countDown();
            }
        }).start();
        c.await();
        System.out.println(3);
    }
}
