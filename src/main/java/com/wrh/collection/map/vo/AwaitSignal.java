package com.wrh.collection.map.vo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wuruohong
 * @Classname AwaitSignal
 * @Description TODO
 * @Date 2020/9/30 1:48
 */
public class AwaitSignal extends ReentrantLock {

    /** 循环次数*/
    private int loopNumber;

    public AwaitSignal(int number){
        this.loopNumber = number;
    }

    /**
     *
     * @param print   输出的字符
     * @param current  当前条件变量
     * @param next    下一个条件变量
     */
    public void print (String print, Condition current, Condition next){
        for (int i = 0; i < loopNumber ; i++) {
            lock();
            try {
                try {
                    /** 获取锁之后等待*/
                    current.await();
                    System.out.println(print);
                }catch (InterruptedException e){

                }
                next.signal();
            }finally {
                unlock();
            }
        }
    }

    public static void main(String[] args) {
        AwaitSignal awaitSignal = new AwaitSignal(5);

        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();

        new Thread(() -> {
            awaitSignal.print("a",a,b);
        }).start();

        new Thread(() -> {
            awaitSignal.print("b",b,c);
        }).start();

        new Thread(() -> {
            awaitSignal.print("c",c,a);
        }).start();

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        awaitSignal.lock();

        try {
            a.signal();
        } finally {
            awaitSignal.unlock();
        }

    }
}
