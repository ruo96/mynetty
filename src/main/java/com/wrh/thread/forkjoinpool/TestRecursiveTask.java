package com.wrh.thread.forkjoinpool;

import java.util.concurrent.ForkJoinPool;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:18 2018/8/21 0021
 * @Modified By:
 */
public class TestRecursiveTask {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);

        long mergedResult = forkJoinPool.invoke(myRecursiveTask);

        System.out.println("合并的结果为: "+ mergedResult);

    }
}
