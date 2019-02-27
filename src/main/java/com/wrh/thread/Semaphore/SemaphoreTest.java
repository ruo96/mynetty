package com.wrh.thread.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:26 2019/2/19 0019
 * @Modified By:
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 200;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for(int i =0;i<THREAD_COUNT; i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println("save data");
//                        返回此信号量中当前可用的许可证数量   返回正在等待获取许可证的线程数
                        System.out.println(s.availablePermits()+ "  ===  " + s.getQueueLength());
                        TimeUnit.SECONDS.sleep(1);
                        s.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPool.shutdown();
    }
}
