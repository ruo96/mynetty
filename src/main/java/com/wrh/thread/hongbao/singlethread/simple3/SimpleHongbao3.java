package com.wrh.thread.hongbao.singlethread.simple3;

import java.util.Scanner;

/**
 * @author wuruohong
 * @Classname SimpleHongbao
 * @Description TODO
 * @Date 2021/4/1 17:59
 */
public class SimpleHongbao3 {
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
        T7 t7=new T7(money,n,p);
        t7.Rob();
    }
}
