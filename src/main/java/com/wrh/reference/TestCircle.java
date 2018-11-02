package com.wrh.reference;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:28 2018/10/30 0030
 * @Modified By:
 */
public class TestCircle {

    public static final int ENDNUMBER = Integer.MAX_VALUE;
    public static final int STARTNUMBER = ENDNUMBER - 2;

    public static void main(String[] args) {
        int count = 0;
        for(int i = STARTNUMBER; i <= ENDNUMBER;i++){   //会越界
            count ++;
        }
        System.out.println(count);


    }

}
