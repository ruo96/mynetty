package com.wrh.basicUse;

import com.alibaba.fastjson.JSON;
import com.wrh.basicUse.vo.ChangVo;
import com.wrh.basicUse.vo.StudentVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:44 2019/10/12 0012
 * @Modified By:
 */
@Slf4j
public class TestBasicUse {

    /**
     * String对象的intern方法会得到字符串对象在常量池中对应的版本的引用（如果常量池中有一个字符串与String对象的equals结果是true），
     * 如果常量池中没有对应的字符串，则该字符串将被添加到常量池中，然后返回常量池中字符串的引用。
     */
    @Test
    public void test(){
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());
    }

    @Test
    public void test1(){
        ChangVo changVo = new ChangVo();
        changVo.setX("X");
        changVo.setY("Y");
        changVo.setA(1);
        changVo.setB(2);

        log.info("=== 1： {}", JSON.toJSONString(changVo));
        changeValue1(changVo);
        log.info("=== 2： {}", JSON.toJSONString(changVo));

    }

    private void changeValue(ChangVo changVo) {

        changVo.setA(changVo.getA() + changVo.getB());
        changVo.setB(changVo.getA() - changVo.getB());
        changVo.setA(changVo.getA() - changVo.getB());
    }

    /**
     * 这种异或的方式最好
     * @param changVo
     */
    private void changeValue1(ChangVo changVo) {

        changVo.setA(changVo.getA()^changVo.getB());
        changVo.setB(changVo.getA()^changVo.getB());
        changVo.setA(changVo.getA()^ changVo.getB());
    }

    @Test
    public void test2(){
        Map<String, Map<String , String>> map = new HashMap<>();
        Map<String , String> map1 = new HashMap<>();
        map1.put("m1","123");
        map.put("wrh",map1);

        Map<String, String> map2 = map.get("wrh");
        map2.put("m2","456");

        log.info("===> map2: {}",map2);
        log.info("===> map: {}",map);
    }

    @Test
    public void test3(){
        int i = 1;
        Integer j = 1;
        log.info("===> i ==j :{}", i==j);

        int i1 = 2000;
        Integer j1 = 2000;
        log.info("===> i1 ==j1 :{}", i1==j1);

        int i2 = 2000;
        Integer j2 = new Integer(2000);
        log.info("===> i2 ==j2 :{}", i2==j2);

        int i3 = 2000;
        Integer j3 = new Integer(2000);
        log.info("===> j3==i3 :{}", j3==i3);
    }


    @Test
    public void test4(){
//        Integer i =null;
//        int j = i;
        log.info("===> {}",Runtime.getRuntime().availableProcessors());

    }

    @Test
    public void test5(){
        Integer i = 12;
        Integer j = 12;

        log.info("+++>{}",i == j);

        Integer i1 = 129;
        Integer j1 = 129;

        log.info("+++>{}",i1 == j1);
        log.info("+++>{}",i1.equals(j1));

    }

    @Test
    public void test6(){
        BigDecimal a = new BigDecimal("1.3336");
        BigDecimal b = new BigDecimal("0.9");
        log.info("===>{}",a.compareTo(b) );

        BigDecimal c = a.setScale(3,BigDecimal.ROUND_HALF_DOWN);
        BigDecimal d = a.setScale(3,BigDecimal.ROUND_HALF_UP);
        BigDecimal e = a.setScale(3,BigDecimal.ROUND_HALF_EVEN);
        BigDecimal f = a.setScale(3,BigDecimal.ROUND_DOWN);
        BigDecimal g = a.setScale(3,BigDecimal.ROUND_CEILING);
        BigDecimal h = a.setScale(3,BigDecimal.ROUND_FLOOR);
        log.info("=c==>{}",c );
        log.info("=d==>{}",d );
        log.info("=e==>{}",e );
        log.info("=f==>{}",f );
        log.info("=g==>{}",g );
        log.info("=h==>{}",h );

    }

    @Test
    public void test7(){
        BigDecimal a = BigDecimal.valueOf(0.1);
        BigDecimal b = BigDecimal.valueOf(0.09);
        log.info("===>{}", a.subtract(b));

    }

    @Test
    public void test8(){
        List<String> list = Arrays.asList("1","2","3");
        log.info("===>{}", JSON.toJSONString(list));

        //这个是不支持的
//        list.add("4");
//        log.info("===>{}", JSON.toJSONString(list));

        List<String> list1 = new ArrayList<>(list);
        log.info("===>{}", JSON.toJSONString(list1));
        list1.add("4");
        log.info("===>{}", JSON.toJSONString(list1));

    }

    @Test
    public void test9(){
        String  a  = "123";
        String  b  = a.intern();
        log.info("===>{}", a == b);
    }

    @Test
    public void test10(){
        Integer a = 20;

        String ahex = Integer.toHexString(a);
        log.info("===>{}",ahex);
        log.info("===>toBinaryString {}",Integer.toBinaryString(a));
        log.info("===>toOctalString {}",Integer.toOctalString(a));
        log.info("===>toUnsignedString radix 8 {}",Integer.toUnsignedString(a,8));
        log.info("===>toUnsignedString {}",Integer.toUnsignedString(a));
    }

    @Test
    public void test11(){
        String a = "a";
        log.info("===> hashcode: {}",a.hashCode());

        Integer b = 123;
        log.info("===> hashcode: {}",b.hashCode());

        Integer c = new Integer(1112);
        log.info("===> hashcode: {}",c.hashCode());

        ChangVo vo = new ChangVo();
        vo.setX("1");
        vo.setY("2");
        vo.setA(3);
        vo.setB(4);
        log.info("===> vo hashcode: {}",vo.hashCode());

        ChangVo vo0 = new ChangVo();
        vo0.setX("1");
        vo0.setY("2");
        vo0.setA(3);
        vo0.setB(4);
        log.info("===> vo0 hashcode: {}",vo0.hashCode());

        StudentVo vo1 = new StudentVo();
        vo1.setName("w1");
        vo1.setAge(1);

        log.info("===> vo1 hashcode: {}",vo1.hashCode());
        StudentVo vo2 = new StudentVo();
        vo2.setName("w1");
        vo2.setAge(1);
        log.info("===> vo2 hashcode: {}",vo2.hashCode());


    }

    @Test
    public void test12(){
        testStrings(null);
    }

    private void testStrings(String... str){
        if(Objects.isNull(str)){
            log.info(" str is null");
        }else {
            log.info("===>{}",str[0]);
        }
    }

    /**
     * 自己设计一个泛型的获取数组最小值的函数，并且这个方法只能接受Number的子类并且实现了Compparable的接口
     */
    @Test
    public void test13(){
        int minInteger = min(new Integer[]{1,2,3});
        double minDouble = min(new Double[]{1.2,2.2,-1d});
//        String typeError = min(new String[]{"1","2"});

        log.info("===>{}",minInteger);
        log.info("===>{}",minDouble);
    }

    private static <T extends Number & Comparable<? super T>> T min(T[] values){
        if(values == null || values.length == 0) {
            return null;
        }
        T min = values[0];
        for (int i = 1; i < values.length ; i++) {
            if(min.compareTo(values[i]) > 0) {
                min = values[i];
            }
        }
        return min;
    }
    
    @Test
    public void test14(){

        Integer a = new Random().nextInt(10);
        System.out.println(a);
    }

    /*private Class test999() {

        *//*public void add(){
            System.out.println();
        }*//*
    }*/

    @Test
    public void test15(){
        ChangVo vo = new ChangVo();
        vo.setX("1");
        vo.setY("2");
        vo.setA(3);
        vo.setB(4);

        changeV(vo);
        System.out.println(vo);


    }

    private void changeV(ChangVo vo) {
        //这个地方就可以看到是值引用了
        vo = new ChangVo();
        vo.setA(999);
    }

    @Test
    public void test16(){
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());

    }

    private static AtomicInteger nextHashCode =
            new AtomicInteger();

    private static final int HASH_INCREMENT = 0x61c88647;

    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    @Test
    public void test17(){



            for (int i = 0; i < 16; i++) {
                System.out.print(nextHashCode() & 15);
                System.out.print(" ");
            }
            System.out.println();
            for (int i = 0; i < 32; i++) {
                System.out.print(nextHashCode() & 31);
                System.out.print(" ");
            }
            System.out.println();
            for (int i = 0; i < 64; i++) {
                System.out.print(nextHashCode() & 63);
                System.out.print(" ");
            }


    }

    @Test
    public void Test() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                int j = (i+5)/(i-5);
            } catch (Exception e) {
                System.out.println(e);
                continue;
            }
        }
    }

    @Test
    public void Test1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
                int j = (i+5)/(i-5);

        }
    }
}
