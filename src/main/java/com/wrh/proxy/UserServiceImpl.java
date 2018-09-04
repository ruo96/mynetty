package com.wrh.proxy;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 8:29 2018/9/4 0004
 * @Modified By:
 */
public class UserServiceImpl implements IUserService {
    @Override
    public void add(String name) {
        System.out.println("向数据库中插入名为: " + name + " 的用户");
    }
}
