package com.wrh.designMode.singleton.EnumTest;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:37 2019/11/8 0008
 * @Modified By:
 */
@Slf4j
public class TestEnumInstance {
    public static void main(String[] args) throws InterruptedException {

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                SingleInstance.INSTANCE.fun1();
                return null;
            }
        };

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 11; i++) {

            Thread task = new Thread(new Runnable() {
                @Override
                public void run() {
                    SingleInstance.INSTANCE.fun1();
                }
            });

            task.join();
            threads.add(task);
        }

        for (Thread t : threads) {
            t.start();
        }

        System.out.println("end!");



    }
}
