package com.wrh.thread.hongbao.singlethread.simple2;

import java.util.Random;

/**
 * @author wuruohong
 * @Classname T5
 * @Description TODO
 * @Date 2021/4/1 18:10
 */
public class T5 {
    double remain;

    int n;
    T5(double money,int n){
        this.remain=money;
        this.n=n;
    }
    int a=1;
    public void Rob(){

        double max;//最大可领红包金额
        double x1;//随机金额
        double x2;//所得金额

        while(n>0) {
            if (n != 1) {//前n-1个红包领取者领的红包为随机金额红包
                max = remain - (n - 1) * 0.01;//最大可领红包金额为剩下的人都获得最小金额0.01
                Random random = new Random();
                x1 = (double) random.nextInt((int) ((max - 0.01) * 100));
                //用nextInt而不用nextDouble的原因是nextDouble无法设置seed
                //上式中max-0.01,下面的x2+0.01即解决了随机数取0导致红包获得者没抢到钱的问题
                x1 /= 100.0;
                x2 = x1 + 0.01;
                remain = remain - x2;
                n--;
                System.out.println("红包获得者" + a + "获取金额为：" + String.format("%.2f", x2) + "元");
                a++;
            } else {//最后一人领的红包为前n-1个人领完后剩下的红包

                System.out.println("红包获得者" + a + "获取金额为：" + String.format("%.2f", remain) + "元");
                n--;
            }
        }

    }
}
