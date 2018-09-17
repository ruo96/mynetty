package com.wrh.basic;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 8:23 2018/9/5 0005
 * @Modified By:
 */
public class Demo {

    public static void main(String[] args) {
        SuperClass clz = new SubClass();
        System.out.println(clz.name);
    }
}
