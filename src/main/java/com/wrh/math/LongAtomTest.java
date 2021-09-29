package com.wrh.math;

/**
 * @author wuruohong
 * @Classname LongAtomTest
 * @Description TODO
 * @Date 2021/6/28 17:59
 */
public class LongAtomTest implements  Runnable {
    private static long field = 0;

    private volatile long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public LongAtomTest(long value) {
        this.setValue(value);
    }
    @Override
    public void run() {
        int i = 0;
        while (i < 100000) {
            LongAtomTest.field = this.getValue();
            i++;
            long temp = LongAtomTest.field;
            if (temp != 1L && temp != -1L) {
                System.out.println("出现错误结果" + temp);
                System.exit(0);
            }

        }
        System.out.println("运行正确");
    }

    public static void main(String[] args) throws InterruptedException {
        String arch = System.getProperty("sun.arch.data.model");
        System.out.println(arch + "-bit");
        LongAtomTest t1 = new LongAtomTest(1);
        LongAtomTest t2 = new LongAtomTest(-1);
        Thread  thread1 = new Thread(t1);
        Thread  thread2 = new Thread(t2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
