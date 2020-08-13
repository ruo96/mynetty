package com.wrh.utils;

import java.math.BigDecimal;

/**
 * @author wuruohong
 * @Classname MathUtils
 * @Description 计算相关工具类
 * @Date 2020/5/26 15:22
 */
public class MathUtils {

    public static String getPercent(long numerator ,long denominator , int fractionDigit){
        BigDecimal percentValue =new BigDecimal(numerator).divide(new BigDecimal(denominator),fractionDigit,BigDecimal.ROUND_HALF_UP);
        return String.valueOf(percentValue);
    }

    public static String getPercentString(long numerator ,long denominator , int fractionDigit){
        BigDecimal percentValue =new BigDecimal(numerator).divide(new BigDecimal(denominator),fractionDigit,BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal("100")).setScale(2);
        System.out.println(percentValue.toString());

        return String.valueOf(percentValue) + "%";
    }
}
