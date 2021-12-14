package com.wrh.speed;



/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:11 2019/1/31 0031
 * @Modified By:
 */
public class TestSpeed {
    public static void main(String[] args) {
        int i = 10000;
        int time = 1000000000;
        long starttime = System.currentTimeMillis();
        double k =0;
        double m = 0;
        for(int j = 0 ; j< time; j++){
            k = i / 100;
        }
        long middletime = System.currentTimeMillis();
        for(int j = 0 ; j< time; j++){
            m = i * 0.01;
        }
        long endTime = System.currentTimeMillis();

        System.out.println("除法的时间:"+ (middletime - starttime));
        System.out.println("乘法的时间:"+ (endTime - middletime));
    }
}
