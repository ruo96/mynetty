package com.wrh.preview.liantong;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:26 2018/8/27 0027
 * @Modified By:
 */
public class TestLianTongB extends TestLianTongA{

    @Override
    int fun2(){
        System.out.println("到达b.fun2()");
        return 456;
    }

    public static void main(String[] args) {
        TestLianTongA a;
        TestLianTongB b = new TestLianTongB();
        b.fun1();
        a = b;
        a.fun1();
    }
}
