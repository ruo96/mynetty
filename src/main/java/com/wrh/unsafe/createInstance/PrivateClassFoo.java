package com.wrh.unsafe.createInstance;

/**
 * @author wuruohong
 * @Classname PrivateClassFoo
 * @Description TODO
 * @Date 2021/10/19 11:01
 */
public class PrivateClassFoo {

    private PrivateClassFoo() {
        System.out.println("construct method is invoked");
    }

    public void hello() {
        System.out.println("private hello world");
    }
}
