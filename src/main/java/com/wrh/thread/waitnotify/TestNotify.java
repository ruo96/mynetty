package com.wrh.thread.waitnotify;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:48 2019/2/18 0018
 * @Modified By:
 */
public class TestNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread waitThread = new Thread(new Wait(),"waitThread");
        Thread nodifyThread = new Thread(new Notify(),"notifyThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        nodifyThread.start();
    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                while(flag){

                    System.out.println(Thread.currentThread() + " 开始运行, 马上要wait, 时间:　"
                            + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(Thread.currentThread() + " 又开始运行了, 时间:　"
                    + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }

    static class Notify implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread() + " 拿到了锁, 开始运行, 马上要notifyAll, 时间:　"
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                /*接下来并不会马上释放锁*/
                flag = false;
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            synchronized (lock){
                System.out.println(Thread.currentThread() + " 又拿到了锁, 时间:　"
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
