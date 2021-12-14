package com.wrh.thread;

/**
 * @author wuruohong
 * @Classname TestUnsafeFactory
 * @Description TODO
 * @Date 2021/12/13 15:28
 */
public class TestUnsafeFactory {
    private int count = 0;

    private boolean flag = true;

    public void refresh() {
        flag = false;
        System.out.println(Thread.currentThread().getName() + "修改flag： " + flag);
    }

    public void load() {
        System.out.println(Thread.currentThread().getName() + "开始执行.....");
        while (flag) {
            count++;

            //LockSupport.unpark(Thread.currentThread());   // 都是靠的内存屏障
            //Thread.yield();
            //try {
            //    Thread.sleep(1);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}

        }
        System.out.println(Thread.currentThread().getName() + "结束执行.....");
    }


    public static void main(String[] args) throws InterruptedException {
        TestUnsafeFactory testUnsafeFactory = new TestUnsafeFactory();
        Thread thread1 = new Thread(()->{
            testUnsafeFactory.load();
        });
        thread1.start();

        Thread.sleep(3);

        Thread thread2 = new Thread(()->{
            testUnsafeFactory.refresh();
        });
        thread2.start();
    }
}
