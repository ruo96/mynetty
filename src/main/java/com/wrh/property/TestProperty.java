package com.wrh.property;

import com.wrh.utils.PropertiesLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;
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

    private static final String configFile = "e:\\config\\app1.properties";

    public static final String SUPPORT_LIST                              = "support.list";

    @Test
    public void Test30() {
        PropertiesLoader loader = new PropertiesLoader(configFile);
        String supportList = loader.getProperty(SUPPORT_LIST);
        System.out.println(supportList);
    }

}
