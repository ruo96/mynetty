package com.wrh.thread.forkjoinpool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:06 2018/8/21 0021
 * @Modified By:
 */
public class MyRecursiveTask extends RecursiveTask<Long> {

    private long workLoad = 0;

    public MyRecursiveTask(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected Long compute() {

        if(this.workLoad > 16){
            System.out.println("线程: "+Thread.currentThread().getName()+"分离任务负担: "+ workLoad);

            List<MyRecursiveTask> subtasks = new ArrayList<>();
            subtasks.addAll(createSubTasks());

            for(MyRecursiveTask subtask: subtasks){
                subtask.fork();
            }

            long result = 0;

            for(MyRecursiveTask subtask: subtasks){
                result += subtask.join();
            }

            return result;
        }else {
            System.out.println("线程: "+Thread.currentThread().getName()+"负担不重,自己完成任务: "+ this.workLoad);
            return workLoad * 3;
        }




    }

    private Collection<MyRecursiveTask> createSubTasks() {

        List<MyRecursiveTask> subtasks = new ArrayList<>();
        MyRecursiveTask subtask1 = new MyRecursiveTask(this.workLoad / 2);
        MyRecursiveTask subtask2 = new MyRecursiveTask(this.workLoad / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);
        return subtasks;
    }


}
