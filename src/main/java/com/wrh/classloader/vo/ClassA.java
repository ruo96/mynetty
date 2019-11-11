package com.wrh.classloader.vo;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:04 2019/10/12 0012
 * @Modified By:
 */
public class ClassA {

    static {
        System.out.print("1");
    }

    public ClassA() {
        System.out.print("2");
    }
}
