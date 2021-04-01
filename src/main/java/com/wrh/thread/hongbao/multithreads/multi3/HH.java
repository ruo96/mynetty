package com.wrh.thread.hongbao.multithreads.multi3;

import java.util.Random;

/**
 * @author wuruohong
 * @Classname T3
 * @Description TODO
 * @Date 2021/4/1 18:32
 */
public class HH implements Runnable {

    double  money;
    int n;
    int count =0;//计数器
    double remain;
    HH(double money,int n){
        this.money=money;//总金额
        this.n=n;//红包数
        this.remain=money;//所剩金额
    }
    @Override
    public synchronized void run() {
        Rob();
    }
    public void Rob(){

        double x1,x2,d;
        String s1,s2;
        Thread th=Thread.currentThread();//获取当前线程
        String th_name=th.getName();//获取线程名字
        Random random=new Random();
        d=money/(n-1);//设置范围让每次所得金额不超过总数的1/(n-1)，这样也就避免了一次取得过大导致后面抢的红包不能保证每个最少0.01
        x1=d*random.nextDouble();
        s1=String.format("%.2f",x1);//转化成字符串型用字符串型的format进行格式化处理，红包金额最多取到小数点后两位
        x1 = Double.parseDouble(s1);//再将字符串型数据转换成double型
        while(x1==0||x1==money/(n-1)){
            x1=d*random.nextDouble();
            s1=String.format("%.2f",x1);//转化成字符串型用字符串型的format进行格式化处理，红包金额最多取到小数点后两位
            x1 = Double.parseDouble(s1);//再将字符串型数据转换成double型
        }
        s2= String.format("%.2f",remain);//转化成字符串型用字符串型的format进行格式化处理，红包金额最多取到小数点后两位
        remain = Double.parseDouble(s2);//再将字符串型数据转换成double型

        if (count<n-1){//前n-1个红包金额为随机金额
            System.out.println(th_name+"抢到了"+s1+"元");
            remain-=x1;
            count++;
        }else if (count==n-1){//第n个为前n-1个红包抢完所剩金额
            System.out.println(th_name+"抢到了"+s2+"元");
            count++;
        }else if (count>n-1){//红包被抢完后再来的
            System.out.println(th_name+"哎呀，手慢了！没抢到！");
            count++;
        }
    }
}
