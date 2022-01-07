package com.wrh.thread.CompletableFutureTest;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author wuruohong
 * @Classname TestCompose
 * @Description thenCompose方法可以将两个异步操作进行流水操作 提供了三个方法
 * @Date 2022/1/4 18:18
 */
public class TestAllOf {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello");
            return null;
        });
        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("world"); return null;
        });
        CompletableFuture<Void> result = CompletableFuture.allOf(future1, future2);
        System.out.println(result.get());
    }

    @Test
    public void Test27() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello");
            return "hello1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("world");
            return "world1";
        });
        CompletableFuture<Void> result = CompletableFuture.allOf(future1, future2);
        System.out.println(result.get());
    }
}
