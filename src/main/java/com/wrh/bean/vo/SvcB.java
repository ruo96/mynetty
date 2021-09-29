package com.wrh.bean.vo;

import org.springframework.stereotype.Service;

/**
 * @author wuruohong
 * @Classname SvcA
 * @Description TODO
 * @Date 2021/7/7 11:23
 */
@Service
public class SvcB implements Svc {
    @Override
    public void sayHello() {
        System.out.println("hello, this is service B");
    }
}
