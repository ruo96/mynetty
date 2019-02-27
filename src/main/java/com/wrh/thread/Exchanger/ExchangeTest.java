package com.wrh.thread.Exchanger;

import java.util.concurrent.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:38 2019/2/19 0019
 * @Modified By:
 */
public class ExchangeTest {

    private static final Exchanger<String> exgr = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String A = "银行流水A";
                try {
                    A = exgr.exchange(A);
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("交换后, A的值:　" + A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String B = "银行流水B123";
                try {
                    String A = exgr.exchange(B);
                    System.out.println("A和B的数据是否一致: " + A.equals(B) + " ,A录入的是:　" + A + " , B录入的是: " + B);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        threadPool.shutdown();
    }
}
