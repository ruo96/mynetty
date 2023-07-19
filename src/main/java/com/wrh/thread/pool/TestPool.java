package com.wrh.thread.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:44 2019/11/19 0019
 * @Modified By:
 */
@Slf4j
public class TestPool {

    @Test
    public void test(){
        ExecutorService service = new ThreadPoolExecutor(8, 8, 0, TimeUnit.MINUTES, new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("my thread").build(), new ThreadPoolExecutor.CallerRunsPolicy());
    }
    
    @Test
    public void Test25() {
        /** 在使用异步CompletableFuture时，无论是否有返回值都要调用get()/join()方法，避免程序执行报错了，仍然返回成功。
         * 如果在程序报错时需要对上一个异步任务结果做其他操作，可以调用whenComplete()、handle()处理，如果只是对异常做处理，
         * 不涉及对上一个异步任务结果的情况，调用exceptionally()处理。*/
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                availableProcessors,
                availableProcessors,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(9999),
                new ThreadFactoryBuilder().setNameFormat("custom-thread-pool-%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
      
    }

    /**
     * 创建线程池
     */
    @Test
    public void Test28() {
        ThreadPoolExecutor threadPool =new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.SECONDS,new ArrayBlockingQueue<>(100),
                // 这里使用 Guava 的 ThreadFactoryBuilder 类，方便构造 ThreadFactory
                new ThreadFactoryBuilder().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        // 处理异常
                    }
                }).build()
        );

        Future<?> future = threadPool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "小黑十一点半";
            }
        });

        try {
            System.out.println("future.get() = " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            // 线程内抛出异常将会被封装在 ExecutionException 内
        }
        
    }
}
