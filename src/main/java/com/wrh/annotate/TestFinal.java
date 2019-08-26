package com.wrh.annotate;

import lombok.extern.slf4j.Slf4j;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:21 2018/10/17 0017
 * @Modified By:
 */

@Slf4j
public class TestFinal {

    /*@Autowired
    public TestAnaotate.info testA;*/


    public void tstAno(){
        System.out.println("classloader is : "+ Thread.currentThread().getContextClassLoader());

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                log.info("classloader1 is : {}",Thread.currentThread().getContextClassLoader());
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                log.info("classloader2 is : {}",Thread.currentThread().getContextClassLoader());
            }
        };

        thread1.start();
        thread2.start();

    }
}
