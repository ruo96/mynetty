package com.wrh.bug;

import com.alibaba.fastjson.JSON;

/**
 * @author wuruohong
 * @Classname BugUser
 * @Description TODO
 * @Date 2021/5/10 14:42
 */
public class BugUser {
    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("调用了name方法");
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("调用了age方法");
        this.age = age;
    }

    /**
     * 加了@type属性就能调用对应对象的setXXX方法，而@type表示指定反序列化成某个类。
     * 如果我们能够找到一个类，而这个类的某个setXXX方法中通过我们的精心构造能够完成命令执行，即可达到攻击的目的啦
     * 后续版本已经去除这个bug
     * @param args
     */
    public static void main(String[] args) {
        String str = "{\"@type\":\"cn.eovie.bean.User\",\"age\":26,\"name\":\"Java3y\"}";
        BugUser user = JSON.parseObject(str,BugUser.class);
    }
}
