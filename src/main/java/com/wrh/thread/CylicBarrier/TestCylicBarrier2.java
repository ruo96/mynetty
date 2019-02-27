package com.wrh.thread.CylicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:53 2019/2/19 0019
 * @Modified By:
 */
public class TestCylicBarrier2 {

    static CyclicBarrier c = new CyclicBarrier(2,new A());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();

        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(2);


    }

    static class A implements Runnable{
        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
