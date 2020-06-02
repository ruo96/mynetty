package com.wrh.reference;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:24 2018/10/30 0030
 * @Modified By:
 */
public class TestNumber {
    public static void main(String[] args) {
        Float totalMoney = 200000.7f;
        Float ownMoney = 170000.5f;
        Float leftMoney = totalMoney - ownMoney;    //数字不精准, 谨慎使用浮点型的加减
        System.out.println("还剩余:" + leftMoney);

        Float totalMoney1 = 18.7f;
        Float ownMoney1 = 2.2f;
        Float leftMoney1 = totalMoney1 - ownMoney1;    //数字不精准, 谨慎使用浮点型的加减
        System.out.println("还剩余:" + leftMoney1);



        Float xx = 200000.7f;
        Float yy = 170000.5f;
        Float tt = xx - yy;

        BigDecimal b1 = new BigDecimal(Float.toString(xx));
        BigDecimal b2 = new BigDecimal(Float.toString(yy));
        float ss = b1.subtract(b2).floatValue();
        System.out.println("ssss----" + ss);
        System.out.println("tttttt-----" + tt);

    }

    @Test
    public void Test1() {
        BigDecimal bigDecimal = new BigDecimal("1.23");
        System.out.println(bigDecimal.doubleValue());
    }
}
