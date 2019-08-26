package com.wrh.string;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 7:57 2019/5/24 0024
 * @Modified By:
 */
public class TestConvert {
    public static void main(String[] args) {
        /*Object obj = new String("10");
        System.out.println((int)obj);*/

        Object obj1 = new String("10");

        System.out.println(Integer.parseInt(String.valueOf(obj1)));
    }
}
