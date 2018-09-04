package com.wrh.proxy.test;

import com.wrh.proxy.IUserService;
import com.wrh.proxy.UserServiceImpl;
import com.wrh.proxy.UserServiceProxy;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 8:34 2018/9/4 0004
 * @Modified By:
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        IUserService target = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy(target);
        proxy.add("wrh");
    }
}
