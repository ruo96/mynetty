package com.wrh.timer;

import java.util.Timer;

/**
 * @author wuruohong
 * @Classname TimerTest
 * @Description TODO
 * @Date 2021/7/2 18:01
 */
public class TimerTest {
    public void test(){
        Timer timer = new Timer();
        timer.schedule(new MyTask(), 800);
    }
}
