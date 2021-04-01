package com.wrh.thread.hongbao.multithreads.multi2;

import java.util.Random;

/**
 * @author wuruohong
 * @Classname T3
 * @Description TODO
 * @Date 2021/4/1 18:32
 */
public class T1 implements Runnable {

    double remain;

    int n;
    T1(double money,int n){
        this.remain=money;
        this.n=n;
    }
    @Override
    public synchronized void run() {

        Rob();
    }
    public void Rob(){

        double max;//最大可领红包金额
        double x1;//随机金额
        double x2;//所得金额

        if(n!=1) {//前n-1个红包领取者领的红包为随机金额红包
            max=remain-(n-1)*0.01;//最大可领红包金额为剩下的人都获得最小金额0.01
            Random random=new Random();
            x1=(double)random.nextInt((int) ((max-0.01)*100));
            //用nextInt而不用nextDouble的原因是nextDouble无法设置seed
            //上式中max-0.01,下面的x2+0.01即解决了随机数取0导致红包获得者没抢到钱的问题
            x1/=100.0;
            x2=x1+0.01;
            remain=remain-x2;
            n=n-1;
            Thread th=Thread.currentThread();//获取当前线程
            String th_name=th.getName();//获取线程名字
            System.out.println(th_name+"获取金额为："+String.format("%.2f", x2)+"元");
        }
        else {//最后一人领的红包为前n-1个人领完后剩下的红包
            Thread th=Thread.currentThread();//获取当前线程
            String th_name=th.getName();//获取线程名字
            System.out.println(th_name+"获取金额为："+String.format("%.2f", remain)+"元");
        }



    }
}
