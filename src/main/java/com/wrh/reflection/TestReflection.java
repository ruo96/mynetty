package com.wrh.reflection;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:25 2019/1/7 0007
 * @Modified By:
 */
public class TestReflection {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> class1 = null;
        Class<?> class2 = null;
        Class<?> class3 = null;
        // 一般采用这种形式
        class1 = Class.forName("com.wrh.reflection.TestReflection");
        class2 = new TestReflection().getClass();
        class3 = TestReflection.class;
        System.out.println("类名称   " + class1.getName());
        System.out.println("类名称   " + class2.getName());
        System.out.println("类名称   " + class3.getName());
    }
}
