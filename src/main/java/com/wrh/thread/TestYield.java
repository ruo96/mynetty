package com.wrh.thread;

import java.util.stream.IntStream;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:17 2018/12/24 0024
 * @Modified By:
 */
public class TestYield {

    public static void main(String[] args) {
        IntStream.range(0,2).mapToObj(TestYield::create).forEach(Thread::start);
    }

    private static Thread create(int index){
        return new Thread(()->{
            if(index == 0 ){
//                System.out.println(index + " yield");
                Thread.yield();

            }
            System.out.println(index);
        });
    }
}
