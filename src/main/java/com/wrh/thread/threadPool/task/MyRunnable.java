package com.wrh.thread.threadPool.task;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:18 2019/11/27 0027
 * @Modified By:
 */
@Slf4j
public class MyRunnable implements Runnable {

    private String command;

    public MyRunnable(String s) {
        this.command = s;
    }

    public MyRunnable() {

    }

    @Override
    public void run() {
      log.info("threadName: {}  start time: {}",Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      processCommand();
      log.info("threadName: {}  end time: {}",Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

    }

    private void processCommand(){
        try {
            log.info("===> this command : {}",this.toString());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {

        return this.command;
    }
}
