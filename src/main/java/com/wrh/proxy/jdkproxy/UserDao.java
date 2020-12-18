package com.wrh.proxy.jdkproxy;

/**
 * @author wuruohong
 * @Classname UserDao
 * @Description TODO
 * @Date 2020/12/18 15:05
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("模拟：保存用户！");
    }

    @Override
    public void find() {
        System.out.println("模拟：查询！");
    }
}
