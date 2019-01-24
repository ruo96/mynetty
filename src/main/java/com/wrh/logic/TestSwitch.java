package com.wrh.logic;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:40 2019/1/22 0022
 * @Modified By:
 */
public class TestSwitch {
    public static void main(String[] args) {
        int i = 1;
        switch (i){
            case 3:
                System.out.println("case3");
            case 2:
                System.out.println("case2");
            case 1:
                System.out.println("case1");
                default:
                    break;
        }
    }
}
