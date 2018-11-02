package com.wrh.string;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:09 2018/11/2 0002
 * @Modified By:
 */
public class TestBasic {

    public static void main(String[] args) {
        int a = 10 >>1;
        int b = a++;
        int c = ++a;
        int d = b * a++;
        System.out.println(a);   //
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }
}
