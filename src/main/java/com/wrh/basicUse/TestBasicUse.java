package com.wrh.basicUse;

import com.alibaba.fastjson.JSON;
import com.wrh.basicUse.vo.ChangVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
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
}
