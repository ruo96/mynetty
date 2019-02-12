package com.wrh.classloader;

import sun.security.ec.SunEC;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:00 2019/2/2 0002
 * @Modified By:
 */
public class TestClassloader {
    public static void main(String[] args) {
        System.out.println("boo+" +
                "tstrap: " + String.class.getClassLoader());
        System.out.println("TestClassloader classloader: " + TestClassloader.class.getClassLoader());
        System.out.println("SunEC classloader: " + SunEC.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }

    /*也可以将自己的类打包成jar包, 放在扩展类加载器所在的路径中, 那么扩展类加载器就会加载你所需要的类*/
}
