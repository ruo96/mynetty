package com.wrh.reflectionOpr;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:54 2019/11/7 0007
 * @Modified By:
 */
@Slf4j
public class TestReflection {

    @Test
    public void test() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName("com.wrh.reflectionOpr.ReflectVo");
        log.info(clazz.getName());

        Method method = clazz.getMethod("getName", String.class);
        Constructor constructor = clazz.getConstructor();
        Object object = constructor.newInstance();
        String result = (String) method.invoke(object, "123");
        log.info("反射结果: {}",result);

    }
}
