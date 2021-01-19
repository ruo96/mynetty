package com.wrh.thread.locksupport;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author wuruohong
 * @Classname TestLockSupport
 * @Description TODO
 * @Date 2021/1/11 19:16
 */
@Slf4j
public class TestLockSupport {

    private static String message;

    @Test
    public void Test15() throws InterruptedException {
        Thread thread1 = new Thread(()->{
            System.out.println("enter thread1");
            LockSupport.park();
            System.out.println("thread1 print: "+message);
        });

        Thread thread2 = new Thread(()->{
            message = "thread2 wakes up thread1";
            System.out.println("enter thread2");
            LockSupport.unpark(thread1);
            System.out.println("exit thread2");
        });

        thread1.start();
        TimeUnit.SECONDS.sleep(3);
        thread2.start();
    }

    /**
     * wait/notify方式的执行顺序会影响到唤醒。
     * BUT  BUT  BUT
     * park/unpark方式的执行顺序不影响唤醒，这是因为park/unpark是使用了许可机制，如果先调用unpark去释放许可，那么调用park时就直接能获取到许可而不必等待。
     * @throws InterruptedException
     */
    @Test
    public void Test41() throws InterruptedException {
        Thread thread1 = new Thread(()->{
            System.out.println("enter thread1");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.park();
            System.out.println("thread1 print: "+message);
        });

        Thread thread2 = new Thread(()->{
            message = "thread2 wakes up thread1";
            System.out.println("enter thread2");
            LockSupport.unpark(thread1);
            System.out.println("exit thread2");
        });

        thread1.start();
        thread2.start();

        TimeUnit.SECONDS.sleep(4);

    }
}
