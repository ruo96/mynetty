package com.wrh.thread.hongbao.multithreads.multi1;

import java.util.Scanner;

/**
 * @author wuruohong
 * @Classname MultiHongbao1
 * @Description TODO
 * @Date 2021/4/1 18:32
 */
public class MultiHongbao1 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n;
        double money;
        System.out.println("请输入您想要发的红包数量");
        n=scanner.nextInt();
        System.out.println("请输入您发送的红包金额");
        money=scanner.nextDouble();
        T3 t3=new T3(n,money);//创建runnable实现类的实例
        for (int j = 1; j <= n; j++) {
            new Thread(t3, "红包获得者" + j).start();//以上面创建的实例作为Thread的参数来创建Thread对象，并为Thread对象指定一个名字，用线程对象的start方法来启动该线程。
        }
    }
}
