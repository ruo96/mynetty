package com.wrh.jvm;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:54 2019/7/6 0006
 * @Modified By:
 */
public class TestClassLoader {
    public static void main(String[] args) {
        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        while (classLoader != null){
            System.out.println(classLoader.toString());
            classLoader = classLoader.getParent();
        }
    }
}
