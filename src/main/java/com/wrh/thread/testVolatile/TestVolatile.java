package com.wrh.thread.testVolatile;

import java.util.concurrent.TimeUnit;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:18 2019/2/11 0011
 * @Modified By:
 */
public class TestVolatile {

    final static int MAX = 5;

    static volatile int init_value = 0;

    public static void main(String[] args) {
        new Thread(()->{
            int localValue = init_value;

            while(localValue < MAX){
                if(init_value != localValue){
                    System.out.printf(" the init_value is updated to [%d]\n",init_value);
                    localValue = init_value;
                }
            }
        },"Reader").start();

        new Thread(()->{
            int localValue = init_value;
            while(localValue < MAX){
                System.out.printf("the init_value will be changed to [%d]\n", ++localValue);
                init_value = localValue;

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Updater").start();
    }
}
