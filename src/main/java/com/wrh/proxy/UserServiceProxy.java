package com.wrh.proxy;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 8:30 2018/9/4 0004
 * @Modified By:
 */
public class UserServiceProxy implements IUserService {

    /*被代理的对象*/
    private IUserService target;

    public UserServiceProxy(IUserService target){
        this.target = target;
    }

    @Override
    public void add(String name) {
        System.out.println("准备向数据库中插入数据");
        target.add(name);
        System.out.println("插入数据成功");
    }
}
