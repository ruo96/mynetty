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
public class TestCombine {

    private static Random random = new Random();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(TestCombine::randomInteger).thenCombine(
                CompletableFuture.supplyAsync(TestCombine::randomInteger), (i, j) -> {
                    System.out.println("i = " + i);
                    System.out.println("j = " + j);
                    return i * j;
                }
        );

        System.out.println(result.get());
    }

    public static Integer randomInteger() {
        return random.nextInt(100);
    }
}
