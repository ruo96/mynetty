package com.wrh.collection.list.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:24 2019/5/7 0007
 * @Modified By:
 */
@Slf4j
public class Thread1 extends Thread {
    @Override
    public void run(){
        log.info("{}正在执行......",Thread.currentThread().getName());
        log.info("线程组为:{}",Thread.currentThread().getThreadGroup().getName());  /*main*/
    }
}
