package com.wrh.thread.CallableTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:15 2019/10/15 0015
 * @Modified By:
 */
@Slf4j
public class CallableTest implements Callable<String> {
    @Override
    public String call() throws Exception {
        String str = "我是返回值";
        return str;
    }

    /**
     * 这个是错误的示例
     */
    @Test
    public void test(){
        CallableTest callableTest = new CallableTest();
        try {
            String str = callableTest.call();
            log.info("===> str: {}", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test1(){
        FutureTask<String> task = new FutureTask<>(new CallableTest());
        new Thread(task).start();

        if(!task.isDone()){
            log.info("任务还没有执行完成！");
        }
        log.info("===> 等待中");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test2() throws ExecutionException, InterruptedException {
        ExecutorService newService = Executors.newCachedThreadPool();
        Future<String> future = newService.submit(new CallableTest());
        if(!future.isDone()){
            log.info("线程尚未结束！");
        }
        log.info("等待中");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("===> future: {}",future.get());
        newService.shutdown();
    }

}
