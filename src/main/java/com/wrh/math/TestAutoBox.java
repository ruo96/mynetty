package com.wrh.math;

import java.util.Properties;
import java.util.Set;

/**
 * @author wuruohong
 * @Classname TestAutoBox
 * @Description TODO
 * @Date 2021/11/25 14:48
 */
public class TestAutoBox {
    public static void main(String[] args) {
        Integer total = 99;
        int totalprim = total;

        Integer.valueOf(totalprim);

        Integer i = 200;
        Integer j = 200;
        //System.setProperty("java.lang.Integer.IntegerCache.high", "250"); 必须改动启动参数才行   改变整型缓存池上限
        System.out.println("i == j = " + (i == j));

        System.out.println("================");
        //System.setProperty("java.lang.Integer.IntegerCache.high", "250");
        System.out.println("System.getProperty(\"java.lang.Integer.IntegerCache.high\") = " + System.getProperty("java.lang.Integer.IntegerCache.high"));
        System.out.println("i == j = " + (i == j));

        //System.setProperty("user.country","moon");

        Properties properties = System.getProperties();
        System.out.println("properties.keySet() = " + properties.keySet());

        Set<Object> set = properties.keySet();
        for (Object b : set) {
            String str = b.toString();
            /*if (str.contains("C")) {

            }*/
            String s = properties.get(b).toString();
            System.out.println(b +"=======>" +s);

        }


    }
}
