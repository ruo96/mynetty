package com.wrh.thread.LocalThread;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 12:02 2019/9/2 0002
 * @Modified By:
 */
public class LocalThread extends Thread {
    private static InheritableThreadLocal local = new InheritableThreadLocal();

    @Override
    public void run() {
        System.out.println("thread线程："+ local.get());
    }

    public static void main(String[] args) throws InterruptedException {
        local.set("main的值");
        LocalThread t = new LocalThread();
        t.start();
        System.out.println("main线程："+ local.get());
    }

}
