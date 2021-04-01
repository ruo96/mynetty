package com.wrh.thread.hongbao.singlethread.simple;

import java.util.Scanner;

/**
 * @author wuruohong
 * @Classname SimpleHongbao
 * @Description TODO
 * @Date 2021/4/1 17:59
 */
public class SimpleHongbao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        double money;
        System.out.println("请输入您想发送的红包数量");
        n = scanner.nextInt();
        System.out.println("请输入您发送的红包金额");
        money = scanner.nextInt();
        T2 t2 = new T2(n,money);
        t2.Rob();
    }
}
