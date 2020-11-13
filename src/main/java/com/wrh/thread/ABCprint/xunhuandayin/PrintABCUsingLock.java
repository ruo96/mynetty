package com.wrh.thread.ABCprint.xunhuandayin;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wuruohong
 * @Classname PrintABCUsingLock
 * @Description TODO
 * @Date 2020/11/4 18:42
 */
@Slf4j
public class PrintABCUsingLock {

    private int times;

    private int state;

    private Lock lock = new ReentrantLock();

    public PrintABCUsingLock(int times) {
        this.times = times;
    }

    private void printLetter(String name, int targetNum){
        for (int i = 0; i < times; ) {
            lock.lock();
            log.info(">>> thread: {} get lock i:{} state: {}", Thread.currentThread().getName(), i, state);
            if(state % 3 == targetNum){
                state ++;
                i++;
                log.info("[i++]>>> thread: {}, value: {}, state: {}  i: {}", Thread.currentThread().getName(), name, state, i);
            }
            log.info(">>> thread: {} lose lock i:{} state: {}", Thread.currentThread().getName(), i, state);
            lock.unlock();

        }
    }

    public static void main(String[] args) {
        PrintABCUsingLock loopThread = new PrintABCUsingLock(2);
        new Thread(()->{
            loopThread.printLetter("B",1);
        },"B").start();

        new Thread(()->{
            loopThread.printLetter("A",0);
        },"A").start();

        new Thread(()->{
            loopThread.printLetter("C",2);
        },"C").start();
    }


}
