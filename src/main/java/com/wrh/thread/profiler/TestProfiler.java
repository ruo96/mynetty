package com.wrh.thread.profiler;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:26 2019/2/19 0019
 * @Modified By:
 */
public class TestProfiler {

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<>();

    public static final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static final void remove(){
        TIME_THREADLOCAL.remove();
    }

    public static void main(String[] args) throws InterruptedException {
        TestProfiler.begin();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("cost: " + TestProfiler.end() + "mills");
    }

    int i = 1;
}
