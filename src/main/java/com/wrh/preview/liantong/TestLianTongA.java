package com.wrh.preview.liantong;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:26 2018/8/27 0027
 * @Modified By:
 */
public class TestLianTongA {
    void fun1(){
        System.out.println("到达a.fun1()");
        System.out.println(fun2());
    }

    int fun2(){
        System.out.println("到达a.fun2()");
        return 123;
    }
}
