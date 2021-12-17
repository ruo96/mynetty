package com.wrh.listener.fourmethods;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author wuruohong
 * @Classname MyListener1
 * @Description TODO
 * @Date 2021/12/17 10:59
 */
@Component
public class MyListener2 implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("MyListener2:" + event.toString());
    }
}
