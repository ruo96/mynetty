package com.wrh.thread.hongbao.multithreads.multi2;

import java.util.Random;

/**
 * @author wuruohong
 * @Classname T3
 * @Description TODO
 * @Date 2021/4/1 18:32
 */
public class T4 implements Runnable {

    double remain;
    int n;
    T4(double money,int n){
        this.remain=money;
        this.n=n;
    }
    public synchronized void run() {
        Rob();
    }
    public void Rob(){
        Thread th=Thread.currentThread();//获取当前线程
        String th_name=th.getName();//获取线程名字
        System.out.println(th_name+"获取金额为："+String.format("%.2f", remain/n)+"元");
    }
}
