package com.wrh.thread.CompletableFutureTest;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author wuruohong
 * @Classname TestCompose
 * @Description thenCompose方法可以将两个异步操作进行流水操作 提供了三个方法
 * @Date 2022/1/4 18:18
 */
public class TestAnyOf {

    private static Random random = new Random();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            randomSleep();
            System.out.println("future1 hello");
            return "hello";});
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            randomSleep();
            System.out.println("future2 world");
            return "world";
        });
        CompletableFuture<Object> result = CompletableFuture.anyOf(future1, future2);
        System.out.println("main "+result.get());

        future1.join();
        future2.join();
    }

    private static void randomSleep() {
        try {
            Thread.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
