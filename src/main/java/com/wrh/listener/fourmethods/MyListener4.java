package com.wrh.listener.fourmethods;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author wuruohong
 * @Classname MyListener1
 * @Description TODO
 * @Date 2021/12/17 10:59
 */
@Component
public class MyListener4{
    /**
     * @param event
     */
    @EventListener
    public void listener(MyEvent event) {
        System.out.println("MyListener4:" + event.getSource());
    }
}
