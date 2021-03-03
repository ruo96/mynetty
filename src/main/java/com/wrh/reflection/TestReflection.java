package com.wrh.reflection;

import com.wrh.elasticsearch.Student;
import org.junit.Test;

import java.util.Arrays;

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


    @Test
    public void Test25() throws ClassNotFoundException {
        Class clz = Class.forName("com.wrh.elasticsearch.Student");
        System.out.println(clz.getClassLoader());
        System.out.println(clz.getName());
        System.out.println(Arrays.toString(clz.getDeclaredFields()));
        System.out.println(clz.getSuperclass());
        System.out.println(Arrays.toString(clz.getInterfaces()));
        System.out.println(Arrays.toString(clz.getMethods()));
//        本内置类型的包装类，都有一个Type属性
        Class c = Integer.TYPE;

    }
    @Test
    public void Test44() {
        int a =0;
        System.out.println("a = " + a);
        System.out.println("a = " + a);

    }
}
