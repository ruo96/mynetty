package com.wrh.math;

import com.wrh.elasticsearch.Student;
import com.wrh.utils.MathUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
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
}
