package com.wrh.thread.CompletableFutureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author wuruohong
 * @Classname TestCompose
 * @Description thenCompose方法可以将两个异步操作进行流水操作 提供了三个方法
 * @Date 2022/1/4 18:18
 */
public class TestCompose {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(TestCompose::getInteger)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> i * 10));
        System.out.println(result.get());
    }

    private static int getInteger() {
        return 666;
    }

    private static int expandValue(int num) {
        return num * 10;
    }
}
