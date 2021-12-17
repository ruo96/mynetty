package com.wrh.listener.fourmethods;

import org.springframework.context.ApplicationListener;

/**
 * @author wuruohong
 * @Classname MyListener1
 * @Description TODO
 * @Date 2021/12/17 10:59
 */
public class MyListener1 implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("MyListener1:" + event.getSource());
    }
}
