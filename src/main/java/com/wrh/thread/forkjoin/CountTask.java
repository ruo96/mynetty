package com.wrh.thread.forkjoin;

import com.wrh.thread.profiler.TestProfiler;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:18 2019/2/19 0019
 * @Modified By:
 */
public class CountTask extends RecursiveTask<Integer> {

    //阈值
    private static final int THRESHOLD = 2;

    private int start;

    private int end;

    public CountTask(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        boolean canComputer = (end - start) > THRESHOLD;

        if(canComputer){
            for(int i = start;i<=end;i++){
                sum += i;
            }
        }else {
            int middle = (start + end)/2;
            CountTask leftTask = new CountTask(start,middle);
            CountTask rightTask = new CountTask(middle+1,end);

            leftTask.fork();
            rightTask.fork();

            int leftResult = leftTask.join();
            int rightResult = leftTask.join();

            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        TestProfiler.begin();
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        CountTask task = new CountTask(1,10000);

        Future<Integer> result = forkJoinPool.submit(task);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("cost: " + TestProfiler.end() + "mills");

        TestProfiler.begin();
        int i = 1;
        int sum = 0;
        for(;i<=10000;i++ ){
            sum += i;
        }
        System.out.println("sum: " + sum);
        System.out.println("cost: " + TestProfiler.end() + "mills");

    }
}
