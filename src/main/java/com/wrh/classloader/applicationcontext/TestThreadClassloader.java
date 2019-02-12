package com.wrh.classloader.applicationcontext;

import static java.lang.Thread.currentThread;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:30 2019/2/3 0003
 * @Modified By:
 */
public class TestThreadClassloader {
    public static void main(String[] args) {
        System.out.println(currentThread().getContextClassLoader());   /*appclassloader*/
    }
}
