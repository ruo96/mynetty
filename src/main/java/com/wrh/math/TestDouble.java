package com.wrh.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.NumberFormat;

/**
 * @author wuruohong
 * @Classname TestDouble
 * @Description TODO
 * @Date 2020/5/27 16:43
 */
@Slf4j
public class TestDouble {

    @Test
    public void Test() {
        System.out.println(getPercent(1.1,2.2,30));
    }
    public static String getPercent(double numerator ,double denominator , int fractionDigit){
        if(denominator == 0) {
            return null;
        }
        double num = numerator  / denominator;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumFractionDigits(fractionDigit);
        numberFormat.setMaximumFractionDigits(fractionDigit);
        return numberFormat.format(num);
    }
}
