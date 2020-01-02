package com.wrh.classloader;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:28 2019/9/4 0004
 * @Modified By:
 */
@Slf4j
public class TestGetLoader {
    @Test
    public void test(){
        log.info(System.getProperty("java.class.path"));
    }

    @Test
    public void test1() {
        log.info(System.getProperty("os.arch"));
        log.info(System.getProperty("sun.arch.data.model"));
    }

    /*public static void main(String[] args) {
        //获取根类加载器所加载的全部URL数组
//        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
//        Arrays.stream(urLs).forEach(System.out::println);

    }*/

    public static void main(String[] args) throws IOException, IOException {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器：" + systemClassLoader);
        /*
        获取系统类加载器的加载路径——通常由CLASSPATH环境变量指定，如果操作系统没有指定
        CLASSPATH环境变量，则默认以当前路径作为系统类加载器的加载路径
         */
        Enumeration<URL> eml = systemClassLoader.getResources("");
        while (eml.hasMoreElements()) {
            System.out.println(eml.nextElement());
        }
        //获取系统类加载器的父类加载器，得到扩展类加载器
        ClassLoader extensionLoader = systemClassLoader.getParent();
        System.out.println("系统类的父加载器是扩展类加载器：" + extensionLoader);
        System.out.println("扩展类加载器的加载路径：" + System.getProperty("java.ext.dirs"));
        System.out.println("扩展类加载器的parant：" + extensionLoader.getParent());
    }
}
