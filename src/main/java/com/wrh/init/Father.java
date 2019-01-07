package com.wrh.init;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:44 2018/11/21 0021
 * @Modified By:
 */
public class Father extends Grandpa {
    static    {
        System.out.println("爸爸在静态代码块");
    }
    public static int factor = 25;
    public Father()    {
        System.out.println("我是爸爸~");
    }
}
