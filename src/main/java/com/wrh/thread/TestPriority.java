package com.wrh.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:12 2018/12/24 0024
 * @Modified By:
 */
public class TestPriority {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while (true){
                System.out.println("------------------------t1-----------------------");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.setPriority(3);

        Thread t2 = new Thread(()->{
            while (true){
                System.out.println("#########################t2#######################");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.setPriority(4);

        t1.start();
        t2.start();
    }
}
