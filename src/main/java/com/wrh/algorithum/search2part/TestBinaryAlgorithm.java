package com.wrh.algorithum.search2part;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:23 2019/11/25 0025
 * @Modified By:
 */
@Slf4j
public class TestBinaryAlgorithm {
    @Test
    public void test(){
        int[] a = {0,1,2,3,4,5,6,7,8,9,10};
        log.info("===>{}",BinarySearch.search(a,8));
    }

    @Test
    public void test1() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName("com.wrh.algorithum.search2part.BinarySearch");
        String name = clazz.getName();
        log.info("===> {}",name);
        Field[] fields = clazz.getFields();
        Field idF = clazz.getDeclaredField("name");
        idF.setAccessible(true);

        Object o = clazz.newInstance();
        idF.set(o, "wrh");
        log.info("===> {}",idF.get(o));

    }
}
