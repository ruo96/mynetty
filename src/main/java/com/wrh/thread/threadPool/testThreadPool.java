package com.wrh.thread.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lmax.disruptor.ExceptionHandler;
import com.wrh.resttemplate.GoogleMapBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:59 2019/11/11 0011
 * @Modified By:
 */
@Slf4j
public class testThreadPool {

    private static RestTemplate restTemplate;

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

    private static class ExceptionHandler implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(">>> 出了异常！！！！");
        }
    }

    private static class MyThreadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread();
            t.setUncaughtExceptionHandler(new testThreadPool.ExceptionHandler());
            return t;
        }
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*ThreadPoolExecutor pool = new ThreadPoolExecutor(2,5,10,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(30),
                Executors.defaultThreadFactory());*/

//        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,5,10,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(30));
        MyThreadPoolExecutor pool = new MyThreadPoolExecutor(2,5,10,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(30));
//        pool.setThreadFactory(new MyThreadFactory());

/*        ExecutorService pool = new ThreadPoolExecutor(8, 16, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(512), Executors.defaultThreadFactory());*/


        Runnable task = () -> {
            log.info(">>> begin :  thread: {}", Thread.currentThread().getName());
            int i1 = 10/0;
            log.info(">>> end :  i1: {}", i1);
        };


        pool.submit(task);
        /*if(future.get()==null){
            log.info("任务无异常");
        }*/

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        log.info(">>> FINAL END");
    }

    @Test
    public void Test76() {



    }
}
