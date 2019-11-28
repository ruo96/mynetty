package com.wrh.basicUse;

import com.alibaba.fastjson.JSON;
import com.wrh.basicUse.vo.ChangVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        list.add("4");
        log.info("===>{}", JSON.toJSONString(list));
    }
}
