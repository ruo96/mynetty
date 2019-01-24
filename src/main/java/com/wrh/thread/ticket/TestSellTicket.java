package com.wrh.thread.ticket;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:55 2019/1/24 0024
 * @Modified By:
 */
public class TestSellTicket implements Runnable{

    private int index = 1;
    private final static int MAX_STOCK = 5000;

    private final static Object MUTEX = new Object();

    @Override
    public void run() {
        while(true){
            synchronized (MUTEX){
                if ( index <= MAX_STOCK){
                    System.out.println(Thread.currentThread() + " 的号码是:　" + (index ++));
                }else {
                    System.out.println(Thread.currentThread() + " 检测到超越了边界条件　" );
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        final TestSellTicket task = new TestSellTicket();

        Thread t1 = new Thread(task,"1号窗口");
        Thread t2 = new Thread(task,"2号窗口");
        Thread t3 = new Thread(task,"3号窗口");
        Thread t4 = new Thread(task,"4号窗口");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
