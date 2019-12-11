package com.wrh.zhengze;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:36 2019/12/11 0011
 * @Modified By:
 */
@Slf4j
public class TestZhengZe {
    @Test
    public void test1(){
//        String str = "北京市(朝阳区)(西城区)(海淀区)";
        String str = "123";
        Pattern p = Pattern.compile("[1-9]");
        Matcher m = p.matcher(str);
        if(m.find()) {
            log.info("{}",m.group());
        }
    }

    @Test
    public void test2() throws ClassNotFoundException {
        String a = "123";
        log.info("{}",a.getClass());
        log.info("{}",String.class);
        log.info("{}",Class.forName("java.lang.String"));

    }

    @Test
    public void test3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String a = "abc";
        Method b = a.getClass().getMethod("toUpperCase");
        log.info("===>{}",b.invoke(a));
    }
}
