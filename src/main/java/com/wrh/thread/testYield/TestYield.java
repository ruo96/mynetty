package com.wrh.thread.testYield;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:10 2018/11/20 0020
 * @Modified By:
 */
public class TestYield {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-----" + i);
                if (i % 20 == 0) {
                    Thread.yield();
                }
            }
        };
        Thread thread1 = new Thread(runnable, "栈长");
        Thread thread2 = new Thread(runnable, "小蜜");

        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);

        thread1.start();
        thread2.start();
    }
}
