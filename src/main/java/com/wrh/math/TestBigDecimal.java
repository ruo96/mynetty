package com.wrh.math;

import com.wrh.elasticsearch.Student;
import com.wrh.utils.ArithmeticUtils;
import com.wrh.utils.MathUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:09 2019/10/21 0021
 * @Modified By:
 */
@Slf4j
public class TestBigDecimal {

    @Test
    public void test(){
        BigDecimal bigDecimal = BigDecimal.valueOf(0.11D);
    }

    @Test
    public void Test() {
        BigDecimal b1 = new BigDecimal("0.99");
        BigDecimal b2 = b1.multiply(BigDecimal.TEN);
        BigDecimal b3 = b1.multiply(new BigDecimal("10"));
        BigDecimal b4 = b1.multiply(BigDecimal.valueOf(10.00f));
        System.out.println(b2.toString());
        System.out.println(b3.toString());
        System.out.println(b4.toString());
    }

    @Test
    public void Test1() {
        double v = 9.9f;
        System.out.println(v);
        double v2 = Double.valueOf(9.9f);
        System.out.println(v2);
    }

    @Test
    public void Test42() {
        System.out.println(MathUtils.getPercentString(100L,500L,4));

    }

    @Test
    public void Test49() {
        Integer i = 100;
        System.out.println(i.toString());

    }

    @Test
    public void Test56() {
        Long a = 4L;
        Long b = 4L;
        long c = a.longValue();
        System.out.println(a.longValue());
        System.out.println(a*b);
        System.out.println(c);

        Long d = null;
        if(Objects.isNull(d)){
            System.out.println("d is null");
        }
    }

    @Test
    public void Test72() {
        Integer i = new Integer(0);
        if(i.equals(0)){
            System.out.println("equal");
        }else {
            System.out.println(i);
        }


    }

    @Test
    public void Test85() {
        BigDecimal b  =new BigDecimal("0.000044456");
        String show;
        BigDecimal percentValue =b.multiply(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_HALF_UP);
//        BigDecimal percentValue =b.multiply(new BigDecimal("100")).setScale(2);
        double a = percentValue.doubleValue();
        System.out.println("a is : " + a);
        /*if(b.compareTo(BigDecimal.ZERO) == 1){
            show =  "+" + String.valueOf(percentValue) + "%";
        }else {
            show =  String.valueOf(percentValue) + "%";
        }*/

        if(a > 0){
            show =  "+" + String.valueOf(percentValue) + "%";
        }else {
            show =  String.valueOf(percentValue) + "%";
        }
        System.out.println(show);

    }

    public static String getPercentString(long numerator ,long denominator , int fractionDigit){
        BigDecimal percentValue =new BigDecimal(numerator).divide(new BigDecimal(denominator),fractionDigit,BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_HALF_UP);
        return String.valueOf(percentValue) + "%";
    }

    public static String getPercentString0(long numerator ,long denominator , int fractionDigit){
        BigDecimal percentValue =new BigDecimal(numerator).divide(new BigDecimal(denominator),fractionDigit,BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal("100")).setScale(0,BigDecimal.ROUND_HALF_UP);
        return String.valueOf(percentValue) + "%";
    }

    @Test
    public void Test114() {
        System.out.println(getPercentString(100000,1,4));
        System.out.println(getPercentString0(100000,3,4));

    }

    public static final Integer BIGDECIMAL_LIMIT = 100;

    public static String getPercentString1(long numerator ,long denominator , int fractionDigit){
        int scale = 2;
        if(numerator / denominator >= BIGDECIMAL_LIMIT){
            scale = 0;
        }
        BigDecimal percentValue =new BigDecimal(numerator).divide(new BigDecimal(denominator),fractionDigit,BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal("100")).setScale(scale,BigDecimal.ROUND_HALF_UP);
        double a = percentValue.doubleValue();
        String show;
        if(a > 0){
            show =  "+" + String.valueOf(percentValue) + "%";
        }else {
            show =  String.valueOf(percentValue) + "%";
        }
        return show;
    }

    public static String getPercentStringWithoutSign(long numerator ,long denominator , int fractionDigit){
        int scale = 2;
        if(numerator / denominator >= BIGDECIMAL_LIMIT){
            scale = 0;
        }
        BigDecimal percentValue =new BigDecimal(numerator).divide(new BigDecimal(denominator),fractionDigit,BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal("100")).setScale(scale,BigDecimal.ROUND_HALF_UP);
        return String.valueOf(percentValue) + "%";
    }
    @Test
    public void Test155() {
        System.out.println(getPercentString1(99, 1 , 4));
        System.out.println(getPercentStringWithoutSign(99, 1 , 4));

    }

    @Test
    public void Test162() {
        BigDecimal amount1 = BigDecimal.valueOf(0.02);
        BigDecimal amount2 = BigDecimal.valueOf(0.03);
        System.out.println("amount2.subtract(amount1) = " + amount2.subtract(amount1));

    }
    @Test
    public void Test169() {
        int i = 1;
        int j = 1;
        System.out.println("i^j = " + (i ^ j));

    }

    public static String removeScientificNotation(Double num) {
        DecimalFormat df = new DecimalFormat("0.##"); // ##表示2位小数
//        DecimalFormat df = new DecimalFormat("0");
        return df.format(num);
    }

    public static String removeScientificNotation2(Double num) {
        DecimalFormat df = new DecimalFormat("0"); // ##表示2位小数
//        DecimalFormat df = new DecimalFormat("0");
        return df.format(num);
    }

    @Test
    public void Test184() {
        Double a = new Double("563.024");
        System.out.println("removeScientificNotation(a) = " + removeScientificNotation(a));
        System.out.println("removeScientificNotation2(a) = " + removeScientificNotation2(a));

        Long b = a.longValue();
        System.out.println("b = " + b);

    }
    
    @Test
    public void Test201() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
        percent.setMaximumFractionDigits(3); //百分比小数点最多3位
        percent.setRoundingMode(RoundingMode.DOWN);

        BigDecimal loanAmount = new BigDecimal("15000.48"); //贷款金额
        BigDecimal interestRate = new BigDecimal("0.0084567"); //利率
        BigDecimal interest = loanAmount.multiply(interestRate); //相乘

        System.out.println("贷款金额:\t" + currency.format(loanAmount));
        System.out.println("利率:\t" + percent.format(interestRate));
        System.out.println("利息:\t" + currency.format(interest));
        
    }

    @Test
    public void Test220() {
        System.out.println("ArithmeticUtils.add(\"123.23\",\"1231\") = " + ArithmeticUtils.add("123.23", "1231"));

    }

    @Test
    public void Test227() {
        BigDecimal a = new BigDecimal("0.1");
        BigDecimal b = new BigDecimal("0.2");
        System.out.println("a.compareTo(b) = " + a.compareTo(b));

    }

    @Test
    public void Test235() {
        /** 计算百分比的新方式*/
        NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用
        NumberFormat usaCurrency = NumberFormat.getCurrencyInstance(Locale.US); //建立货币格式化引用
        NumberFormat ukCurrency = NumberFormat.getCurrencyInstance(Locale.UK); //建立货币格式化引用
        NumberFormat geCurrency = NumberFormat.getCurrencyInstance(Locale.GERMAN); //建立货币格式化引用
        NumberFormat JACurrency = NumberFormat.getCurrencyInstance(Locale.JAPAN); //建立货币格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
        percent.setMaximumFractionDigits(3); //百分比小数点最多3位

        BigDecimal loanAmount = new BigDecimal("15000.48"); //贷款金额
        BigDecimal interestRate = new BigDecimal("0.008"); //利率
        BigDecimal interest = loanAmount.multiply(interestRate); //相乘

        System.out.println("贷款金额:\t" + currency.format(loanAmount));
        System.out.println("贷款金额(美元):\t" + usaCurrency.format(loanAmount));
        System.out.println("贷款金额(英镑):\t" + ukCurrency.format(loanAmount));
        System.out.println("贷款金额(德国马克):\t" + geCurrency.format(loanAmount));
        System.out.println("贷款金额(日元):\t" + JACurrency.format(loanAmount));
        System.out.println("利率:\t" + percent.format(interestRate));
        System.out.println("利息:\t" + currency.format(interest));
    }
    
    @Test
    public void Test260() {
        System.out.println(formatToNumber(new BigDecimal("3.435")));
        System.out.println(formatToNumber(new BigDecimal(0)));
        System.out.println(formatToNumber(new BigDecimal("0.00")));
        System.out.println(formatToNumber(new BigDecimal("0.001")));
        System.out.println(formatToNumber(new BigDecimal("0.006")));
        System.out.println(formatToNumber(new BigDecimal("0.206")));
        
    }

    /**
     * @desc 1.0~1之间的BigDecimal小数，格式化后失去前面的0,则前面直接加上0。
     * 2.传入的参数等于0，则直接返回字符串"0.00"
     * 3.大于1的小数，直接格式化返回字符串
     * @param
     * @return
     */
    public static String formatToNumber(BigDecimal obj) {
        DecimalFormat df = new DecimalFormat("#.00");
        if(obj.compareTo(BigDecimal.ZERO)==0) {
            return "0.00";
        }else if(obj.compareTo(BigDecimal.ZERO)>0&&obj.compareTo(new BigDecimal(1))<0){
            return "0"+df.format(obj).toString();
        }else {
            return df.format(obj).toString();
        }
    }


}
