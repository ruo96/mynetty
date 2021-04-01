package com.wrh.thread.hongbao.singlethread.simple2;

import java.util.Scanner;

/**
 * @author wuruohong
 * @Classname SimpleHongbao
 * @Description TODO
 * @Date 2021/4/1 17:59
 */
public class SimpleHongbao2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        double money=0;
        System.out.println("请输入您想发送的红包数量");
        n = scanner.nextInt();
        System.out.println("请输入您发送的红包金额");
        money = scanner.nextInt();
        if (money / n == 0.01) {
            T6 t6 = new T6(money, n);
            t6.Rob();
        } else {
            T5 t5= new T5(money, n);
            t5.Rob();
        }
    }
}
