package com.wrh.init;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:44 2018/11/21 0021
 * @Modified By:
 */
public class Son extends Father {
    static     {
        System.out.println("儿子在静态代码块");
    }
    public Son()    {
        System.out.println("我是儿子~");
    }
}
