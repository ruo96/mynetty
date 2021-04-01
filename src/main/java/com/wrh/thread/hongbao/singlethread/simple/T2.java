package com.wrh.thread.hongbao.singlethread.simple;

import java.util.Random;

/**
 * @author wuruohong
 * @Classname T2
 * @Description TODO
 * @Date 2021/4/1 18:02
 */
public class T2 {
    public double remain;//有红包被领取后的余额
    int n;//红包数量

    T2(int n,double money) {
        this.remain=money;
        this.n=n;

    }
    int a=1;
    public void Rob() {

        while (n > 0) {
            double x2;

            if (n != 1) {//因为最后一个人领取金额为前面人领取红包后剩下的，所以无需再进行随机
                x2 = process();//取随机金额
                while (judge(x2) != 1) {//判断取到的随机金额是否非法，即无法保证后来每个红包领取者领到最低金额0.01
                    x2 = process();//若非法则重新取随机金额
                }

                remain = remain - x2;//当领取成功后余额减去领走的金额
                n--;//确保每次判断人数为所剩红包数减1
                System.out.println("红包获得者" + a + "获得" + x2 + "元");//此处默认领取者顺序为升序
                a++;//控制输出顺序
            }
            else {
                x2 = remain;//因为最后一个人领取金额为前面人领取红包后剩下的，所以无需再进行随机
                String str = String.valueOf(x2);
                String str1 = String.format("%.2f", x2);
                x2 = Double.parseDouble(str1);
                System.out.println("红包获得者" + a + "获得" + x2 + "元");
                n--;//确保每次判断人数为所剩红包数减1
            }


        }

    }
    public int judge(double x){//判断函数
        if (remain - x > (n - 1) * 0.01) {//确保后来红包领取者最少能领到最低金额0.01
            return 1;
        } else {
            return 0;
        }
    }

    public double process() {//实现红包金额随机的函数
        double x2;
        double x1;
        String str1;
        Random random = new Random();//随机数为取0到1之间的任意double值
        x1 = remain*random.nextDouble();
        str1= String.format("%.2f",x1);//转化成字符串型用字符串型的format进行格式化处理，红包金额最多取到小数点后两位
        x2=Double.parseDouble(str1);//再将字符串型数据转换成double型
        while(x2==0){//如果所取金额非法则回炉重造
            x1 = remain*random.nextDouble();
            str1= String.format("%.2f",x1);//转化成字符串型用字符串型的format进行格式化处理，红包金额最多取到小数点后两位
            x2=Double.parseDouble(str1);//再将字符串型数据转换成double型
        }
        return x2;


    }
}
