package com.wrh.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:29 2018/8/20 0020
 * @Modified By:
 */
@Data
@Slf4j
public class MyThread extends Thread {

    @Override
    public void run(){
        log.info("{}正在执行......",Thread.currentThread().getName());
        log.info("线程组为:{}",Thread.currentThread().getThreadGroup().getName());  /*main*/
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread thread = new MyThread();
        thread.start();

        //thread也是实现的接口

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("running");
            }
        };

        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        };

        FutureTask<Integer> task1 = new FutureTask<>(task);
        ExecutorService service = new ThreadPoolExecutor(8,16,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                new ThreadFactoryBuilder().setNameFormat("task-%d").build());

        service.submit(task1);
        log.info("===> result: {}",String.valueOf(task1.get()));

    }



    @Test
    public void Test66() throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(() -> {
            System.out.println(System.currentTimeMillis() + "开始执行");
            //模拟任务耗时
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "执行结束");
        }, 2, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(10);
    }
}
