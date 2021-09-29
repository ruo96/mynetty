package com.wrh.string;

import cn.hutool.core.util.NumberUtil;
import com.wrh.elasticsearch.Student;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:50 2019/7/12 0012
 * @Modified By:
 */
public class TestNum {
    public static void main(String[] args) {
        String a = "1.11";
        String b = "1.00";
        System.out.println(isInteger(a));
        System.out.println(isInteger(b));

    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    @Test
    public void Test1() {
        Student s = new Student();

        long b = s.getGrade();
//        System.out.println(b);
    }

    @Test
    public void Test36() {

        int i = NumberUtil.parseInt("1.0");
        System.out.println(i);

    }

    @Test
    public void Test45() {
        Student s = null;
        s.setTitle("w1");
        System.out.println(s);

    }

    @Test
    public void Test53() {
        Long a = 10002L;
        Double b = 6.48088;
        System.out.println("String.valueOf(a / 100 / b) = " + String.valueOf(a / 100 / b));

        Double c = a / 100 / b;
        System.out.println("c = " + c);
        System.out.println("BigDecimal.valueOf(c).setScale(2).toString() = " + BigDecimal.valueOf(c).setScale(2, RoundingMode.DOWN).toString());
        System.out.println("BigDecimal.valueOf(c).longValue() = " + BigDecimal.valueOf(c).longValue());

    }

    @Test
    public void Test68() {
        Integer i = 2;
        boolean a = (i==1);
        boolean b = i==1;
        System.out.println("a = " + a);
        System.out.println("b = " + b);

    }

    @Test
    public void Test78() {
        int i = 100;
        Double j = 1.3;
        int b = (int) (i/j);
        System.out.println("b = " + b);

    }
}
