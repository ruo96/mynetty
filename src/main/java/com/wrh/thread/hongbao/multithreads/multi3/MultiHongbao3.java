package com.wrh.thread.hongbao.multithreads.multi3;

import java.util.Scanner;

/**
 * @author wuruohong
 * @Classname MultiHongbao1
 * @Description TODO
 * @Date 2021/4/1 18:32
 */
public class MultiHongbao3 {
    public static void main(String[] args) {
        int p,n;
        double money;
        System.out.println("请输入您发送的红包金额");
        Scanner scanner=new Scanner(System.in);
        money=scanner.nextDouble();
        System.out.println("请输入您发送的红包数量");
        n=scanner.nextInt();
        System.out.println("请输入参与抢红包的人数");
        p=scanner.nextInt();
        HH hh=new HH(money,n);
        for (int i=1;i<=p;i++){
            new Thread(hh,"第"+i+"个人").start();
        }
    }

}
