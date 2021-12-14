package com.wrh.spring.source;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author wuruohong
 * @Classname MyListcycle
 * @Description TODO
 * @Date 2021/12/2 18:36
 */
@Component
public class MyLifecycle implements SmartLifecycle {

    private boolean isRunning;


    @Override
    public void start() {
        System.out.println("spring start finished");
        isRunning = true;
    }

    @Override
    public void stop() {
        System.out.println("spring end run");
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public boolean isAutoStartup() {
        return false;
    }

    @Override
    public void stop(Runnable callback) {

    }

    @Override
    public int getPhase() {
        return 1;
    }
}
