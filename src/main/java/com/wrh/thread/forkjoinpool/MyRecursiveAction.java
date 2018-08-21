package com.wrh.thread.forkjoinpool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:54 2018/8/21 0021
 * @Modified By:
 */
public class MyRecursiveAction extends RecursiveAction {

    private long workLoad = 0;

    public MyRecursiveAction(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {
        if(this.workLoad > 16){
            System.out.println("分割 workLoad:" + this.workLoad);

            List<MyRecursiveAction> subtasks = new ArrayList<>();

            subtasks.addAll(createSubtasks());

            for(RecursiveAction subtask: subtasks){
                subtask.fork();
            }
        }else {
            System.out.println("负担不重, 自己完成任务. workLoad: "+ this.workLoad);
        }
    }

    private List<MyRecursiveAction> createSubtasks() {

        List<MyRecursiveAction> subtasks = new ArrayList<>();

        MyRecursiveAction subtask1 = new MyRecursiveAction(this.workLoad/2);
        MyRecursiveAction subtask2 = new MyRecursiveAction(this.workLoad/2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }
}
