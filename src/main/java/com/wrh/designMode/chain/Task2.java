package com.wrh.designMode.chain;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 12:24 2019/11/25 0025
 * @Modified By:
 */
public class Task2 implements Task {

    private Task task;

    public Task2(){}

    public Task2(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        System.out.println("task2 is run");
        if(task != null){
            task.run();
        }
    }
}
