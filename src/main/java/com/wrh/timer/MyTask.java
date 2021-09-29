package com.wrh.timer;

import java.time.LocalDateTime;
import java.util.TimerTask;

/**
 * @author wuruohong
 * @Classname MyTask
 * @Description TODO
 * @Date 2021/7/2 18:02
 */
public class MyTask extends TimerTask {


    @Override
    public void run() {
        System.out.println("输入定时任务, "+LocalDateTime.now().toString());
    }
}
