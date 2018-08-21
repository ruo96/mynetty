package com.wrh.thread.forkjoinpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:01 2018/8/21 0021
 * @Modified By:
 */
@Slf4j
public class TestForkJoin {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);

        forkJoinPool.invoke(myRecursiveAction);

    }
}
