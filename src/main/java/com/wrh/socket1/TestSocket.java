package com.wrh.socket1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:37 2019/3/1 0001
 * @Modified By:
 */
public class TestSocket {

    public static void main(String[] args) throws UnknownHostException {

        InetAddress add=InetAddress.getLocalHost();//获得本机的InetAddress对象
        System.out.println(add.getHostAddress());//返回本机IP地址
        System.out.println(add.getHostName());//输出计算机名

        System.out.println(Runtime.getRuntime().availableProcessors());
    }


}
