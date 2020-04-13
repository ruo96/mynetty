package com.wrh.utils.test;

import com.wrh.utils.PropertiesLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname TestPropertiesLoader
 * @Description TODO
 * @Date 2020/4/1 11:48
 */
@Slf4j
public class TestPropertiesLoader {

    /**
     * 读取配置文件
     */
    @Test
    public void Test() {

        PropertiesLoader propertiesLoader = new PropertiesLoader("app1.properties");
        System.out.println(propertiesLoader.getInteger("testproperty"));
    }
}
