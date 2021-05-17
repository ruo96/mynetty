package com.wrh.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author wuruohong
 * @Classname MyFactoryBean
 * @Description TODO
 * @Date 2021/5/7 15:50
 */
public class MyFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        String data1 = buildData1();
        String data2 = buildData2();

        return buildData3(data1,data2);
    }

    private String buildData1() {
        return "data1";
    }

    private String buildData2() {
        return "data2";
    }

    private String buildData3(String data1, String data2) {
        return data1 + data2;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
