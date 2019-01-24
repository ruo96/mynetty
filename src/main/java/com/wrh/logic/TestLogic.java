package com.wrh.logic;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:53 2019/1/22 0022
 * @Modified By:
 */
public class TestLogic {
    public static void main(String[] args) {
        int i = 1;
        int j = 2;

        if( i == 1 ^ j == 2){//true  true
            System.out.println("异或结果为true");
        }else {
            System.out.println("异或结果为false");
        }

        System.out.println("==========================");

        if( i == 1 ^ j == 1){//true  false
            System.out.println("异或结果为true");
        }else {
            System.out.println("异或结果为false");
        }
    }
}
