package com.wrh.classloader.vo;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:04 2019/10/12 0012
 * @Modified By:
 */
public class ClassB extends ClassA {

    static {
        System.out.print("a");
    }

    public ClassB() {
        System.out.print("b");
    }
}
