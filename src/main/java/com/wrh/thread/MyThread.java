package com.wrh.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:29 2018/8/20 0020
 * @Modified By:
 */
@Data
@Slf4j
public class MyThread extends Thread {

    @Override
    public void run(){
        log.info("{}正在执行......",Thread.currentThread().getName());
        log.info("线程组为:{}",Thread.currentThread().getThreadGroup().getName());  /*main*/
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}
