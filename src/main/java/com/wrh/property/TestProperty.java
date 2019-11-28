package com.wrh.property;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:38 2019/11/11 0011
 * @Modified By:
 */
@Slf4j
public class TestProperty {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty("name1","张三");
        properties.setProperty("name2","张四");
        properties.setProperty("name3","张五");
        //将 properties 作为参数初始化到 newProperties 中
        Properties newProperties = new Properties(properties);
        newProperties.setProperty("name4","李三");
        //查询key中 name1 的值
        System.out.println("查询结果：" + newProperties.getProperty("name1"));
    }

}
