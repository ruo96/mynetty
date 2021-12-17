package com.wrh.thread.runbystep;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:42 2019/10/12 0012
 * @Modified By:
 */
public class MyService {
    private volatile int orderNum = 1;

    private static String lock = "";

    public synchronized void methodA() {
        synchronized (lock) {

        }
        try {
            while (orderNum != 1) {
                wait();
            }
            for (int i = 0; i < 2; i++) {
                System.out.println("AAAAA");
            }
            orderNum = 2;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void methodB() {
        try {
            while (orderNum != 2) {
                wait();
            }
            for (int i = 0; i < 2; i++) {
                System.out.println("BBBBB");
            }
            orderNum = 3;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void methodC() {
        try {
            while (orderNum != 3) {
                wait();
            }
            for (int i = 0; i < 2; i++) {
                System.out.println("CCCCC");
            }
            orderNum = 1;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
