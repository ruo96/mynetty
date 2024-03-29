package com.wrh.classloader;

import com.wrh.classloader.vo.ClassA;
import com.wrh.classloader.vo.ClassB;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:00 2019/2/2 0002
 * @Modified By:
 */
@Slf4j
@Data
public class TestClassloader {

    private String name;

    /*也可以将自己的类打包成jar包, 放在扩展类加载器所在的路径中, 那么扩展类加载器就会加载你所需要的类*/


    @Test
    public void test(){
        System.out.println("boo+" +
                "tstrap: " + String.class.getClassLoader());
        System.out.println("TestClassloader classloader: " + TestClassloader.class.getClassLoader());
//        System.out.println("SunEC classloader: " + SunEC.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }

    /**
     * 创建对象时构造器的调用顺序是：先初始化静态成员，然后调用父类构造器，再初始化非静态成员，最后调用自身构造器。
     */
    @Test
    public void test1(){
        ClassA ab = new ClassB();
        log.info("======");
        ab = new ClassB();
    }

    /**
     * JVM 默认用于加载用户程序的 ClassLoader 为 AppClassLoader，不过无论是什么ClassLoader，它的根父类都是 java.lang.ClassLoader。
     * 在上面那个例子中，loadClass（）方法最终会调用到 ClassLoader.definClass1（）中，这是一个 Native 方法。
     * @throws ClassNotFoundException
     */
    @Test
    public void test2() throws ClassNotFoundException {
        TestClassloader.class.getClassLoader().loadClass("com.wrh.classloader.TestClassloader");
        log.info("===> classloader: {}", TestClassloader.class.getClassLoader());
        log.info("===> classloader : {}", TestClassloader.class.getClassLoader().getParent());
        log.info("===> classloader: {}", TestClassloader.class.getClassLoader().getParent().getParent());
    }

    @Test
    public void Test56() {


    }

    public static void main(String[] args) {
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(appClassLoader);
        ClassLoader extClassloader = appClassLoader.getParent();
        System.out.println(extClassloader);
        ClassLoader bootstrapLoader = appClassLoader.getParent();
        System.out.println(bootstrapLoader);

        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }

        System.out.println("extClassloader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));


    }
    
    @Test
    public void Test88() throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException {
        Class clazz = Class.forName("com.wrh.classloader.TestClassloader");

        // 获取类名字
        System.out.println(clazz.getName()); // 包名 + 类名
        System.out.println(clazz.getSimpleName()); // 类名

        // 获取类属性
        System.out.println("================");
        // 只能找到public属性
        Field[] fields = clazz.getFields();

        // 找到全部的属性
        Field [] fieldAll = clazz.getDeclaredFields();

        for (int i = 0; i < fieldAll.length; i++) {
            System.out.println(fieldAll[i]);
        }

        // 获取指定属性的值
        Field name = clazz.getDeclaredField("name");

        // 获取方法
        Method[] methods = clazz.getDeclaredMethods(); // 获取本类和父类的所有public方法
        Method [] methods2 = clazz.getMethods(); // 获取本类所有方法

        // 获得指定方法
        Method method = clazz.getDeclaredMethod("getName", null);

        // 获取方法的时候，可以把参数也丢进去，这样因为避免方法重载，而造成不知道加载那个方法
        Method method2 = clazz.getDeclaredMethod("setName", String.class);


    }

}
