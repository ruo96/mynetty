package com.wrh.math;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:43 2019/6/11 0011
 * @Modified By:
 */
public class TestBitMap {
    public static void main(String[] args) {
        int number = 11;
        int position = number & 0x07;
        System.out.println(position);
    }
}
