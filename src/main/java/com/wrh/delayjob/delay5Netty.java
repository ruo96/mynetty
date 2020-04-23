package com.wrh.delayjob;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author wuruohong
 * @Classname delay5Netty
 * @Description TODO
 * @Date 2020/4/16 19:30
 */
public class delay5Netty {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("程序启动时间：" + LocalDateTime.now());
        NettyTask();
        TimeUnit.SECONDS.sleep(50);
    }

    private static void NettyTask() {
        HashedWheelTimer timer = new HashedWheelTimer(3, TimeUnit.SECONDS,100); //最后一个为时间轮中的槽数

        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("执行任务：" + ",执行时间:" + LocalDateTime.now());
            }
        };
        timer.newTimeout(task, 0, TimeUnit.SECONDS);
    }
}
