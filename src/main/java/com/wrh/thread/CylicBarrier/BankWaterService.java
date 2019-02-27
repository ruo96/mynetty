package com.wrh.thread.CylicBarrier;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:04 2019/2/19 0019
 * @Modified By:
 */
public class BankWaterService implements Runnable{

    private CyclicBarrier c = new CyclicBarrier(4,this);

    private Executor executor = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String,Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    private void count(){
        for(int i =0;i<4;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sheetBankWaterCount.put(Thread.currentThread().getName(),1);
                    try {
                        System.out.println(Thread.currentThread() + " begin wait");
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        System.out.println("等待的阻塞线程数: " + c.getNumberWaiting());
        System.out.println("阻塞的线程是否被中断: " + c.isBroken());
        System.out.println("begin heji");
        int result = 0;

        for(Map.Entry<String,Integer> sheet:sheetBankWaterCount.entrySet()){
            result += sheet.getValue();

        }
        sheetBankWaterCount.put("result",result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }
}
