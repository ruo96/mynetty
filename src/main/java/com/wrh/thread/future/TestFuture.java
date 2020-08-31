package com.wrh.thread.future;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:12 2019/2/11 0011
 * @Modified By:
 */
public class TestFuture {

    public static void main(String[] args) {
        /*Future*/
    }


    /**
     * 了解了Future的使用，这里就要谈谈Future的局限性。Future很难直接表述多个Future 结果之间的依赖性，开发中，我们经常需要达成以下目的：
     *
     *     将两个异步计算合并为一个（这两个异步计算之间相互独立，同时第二个又依赖于第一个的结果）
     *
     *     等待 Future 集合中的所有任务都完成。
     *     仅等待 Future 集合中最快结束的任务完成，并返回它的结果。
     *
     *     这种情况就需要completablefuture
     */
    @Test
    public void Test17() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> result = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return LocalDateTime.now().getDayOfYear();
            }
        });
        executor.shutdown();

        try {
            System.out.println("result:" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test53() throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() +"："+ "开始烧开水");
                TimeUnit.SECONDS.sleep(4);
                System.out.println(Thread.currentThread().getName() +"："+ "开水烧好了");
                return "开水";
            }
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println(Thread.currentThread().getName() +":"+"此时开启了一个线程执行future逻辑（烧开水）");
        // 模拟准备火锅食材耗时
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() +":"+"火锅食材准备好了");
        String shicai = "火锅食材";
        String kaishui = futureTask.get();

        System.out.println(Thread.currentThread().getName() +":"+kaishui+"和"+shicai+"此时开启了一个线程执行future逻辑（烧开水）");

    }
}
