package com.wrh.thread.hongbao.multithreads.multi2;

import java.util.Scanner;

/**
 * @author wuruohong
 * @Classname MultiHongbao1
 * @Description TODO
 * @Date 2021/4/1 18:32
 */
public class MultiHongbao2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        double money=0;//红包总金额
        int n;//红包个数
        System.out.println("请输入您想要发的红包数量");
        n=scanner.nextInt();
        System.out.println("请输入您发送的红包金额");
        money=scanner.nextDouble();
        if(money/n==0.01){//当所发金额刚好为每人0.01元时
            T4 t4=new  T4(money,n);
            for(int i=1;i<=n;i++) {
                new Thread(t4,"红包获得者"+i).start();
            }
        }else{
            T1 t1=new  T1(money,n);
            for(int i=1;i<=n;i++) {
                new Thread(t1,"红包获得者"+i).start();
            }
        }
    }

}
