package com.wrh.aop.jdkaop;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wuruohong
 * @Classname ProxyUtils
 * @Description TODO
 * @Date 2021/10/18 16:31
 */
public class ProxyUtils {
    public static void main(String[] args) {
        Star star = new Star("Eminem");
        generateClassFile(star.getClass(), "StarProxy");
    }

    public static void generateClassFile(Class clazz, String proxyName) {

        //根据类信息和提供的代理类名称，生成字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        String paths = clazz.getResource(".").getPath();
        System.out.println(paths);
        FileOutputStream out = null;

        try {
            //保留到硬盘中
            out = new FileOutputStream(paths + proxyName + ".class");
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
