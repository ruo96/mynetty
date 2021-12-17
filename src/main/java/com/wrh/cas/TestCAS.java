package com.wrh.cas;

import com.wrh.unsafe.UnsafeUtils;
import org.junit.Test;
import sun.misc.Unsafe;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wuruohong
 * @Classname TestCAS
 * @Description TODO
 * @Date 2021/12/16 15:54
 */
public class TestCAS {
    private volatile static int sum = 0;
    static Object object = "";
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                lock.lock();
                try {
                    for (int j = 0; j < 10000; j++) {
                        sum++;
                    }
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
            thread.join();
        }

        System.out.println("sum = " + sum);
    }

    class Entity{
        private int x;
    }
    @Test
    public void Test36() {
        Entity entity = new Entity();

        Unsafe unsafe = UnsafeUtils.unsafe;


    }

}
