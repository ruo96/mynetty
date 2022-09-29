package com.wrh.jvm;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/9/13 10:50
 */
public class TestSafepoint {

    public static AtomicInteger num = new AtomicInteger(0);

    @SneakyThrows
    @Test
    public void Test11() {
        Runnable runnable=()->{
            for (int i = 0; i < 1000000000; i++) {
                num.getAndAdd(1);
            }
            System.out.println(Thread.currentThread().getName()+"执行结束!");
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("num = " + num);
      
    }

    /**
     * 这个循环就属于前面说的可数循环（Counted Loop）。
     *
     * 这个程序发生了什么事情呢？
     *
     * 1.启动了两个长的、不间断的循环（内部没有安全点检查）。
     * 2.主线程进入睡眠状态 1 秒钟。
     * 3.在 1000 ms 之后，JVM 尝试在 Safepoint 停止，以便 Java 线程进行定期清理，但是直到可数循环完成后才能执行此操作。
     * 4.主线程的 Thread.sleep 方法从 native 返回，发现安全点操作正在进行中，于是把自己挂起，直到操作结束。
     * 所以，当我们把 int 修改为 long 后，程序就表现正常了：
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable=()->{
            for (int i = 0; i < 1000000000; i++) {   // 也可以不加try catch ， 直接修改类型为long也是可以的
                num.getAndAdd(1);
                if (i % 1000 == 0) {
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(Thread.currentThread().getName()+"执行结束!");
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("num = " + num);
    }
}
