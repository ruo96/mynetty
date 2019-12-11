package com.wrh.thread.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:59 2019/11/11 0011
 * @Modified By:
 */
@Slf4j
public class testThreadPool {

    @Test
    public void test() throws InterruptedException {
         ExecutorService service = new ThreadPoolExecutor(8,16,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                new ThreadFactoryBuilder().setNameFormat("my_task-%d").build());

//         ExecutorService service1 = new Th

        ExecutorService service1 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

        ExecutorService service2 = Executors.newSingleThreadExecutor();
        ExecutorService service3 = Executors.newCachedThreadPool();
        ExecutorService service4 = Executors.newScheduledThreadPool(10);

        service.submit(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        log.info("线程{} 休息完毕", Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        TimeUnit.SECONDS.sleep(4);
        log.info("主线程{}结束",Thread.currentThread().getName());
    }

    @Test
    public void test1(){
//        ExecutorService service = new ThreadPoolExecutor(1,1,);
        ExecutorService service = Executors.newSingleThreadExecutor();
        ExecutorService service1 = Executors.newFixedThreadPool(1);
        ExecutorService service2 = Executors.newCachedThreadPool();
        ExecutorService service3 = Executors.newScheduledThreadPool(1);
        ExecutorService service4 = Executors.newSingleThreadExecutor();
    }
}
