package com.wrh.unsafe.createInstance;

import com.wrh.unsafe.UnsafeUtils;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author wuruohong
 * @Classname TestCreateInstance
 * @Description TODO
 * @Date 2021/10/19 11:02
 */
public class TestCreateInstance {

    @Test
    public void Test16() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<PrivateClassFoo> con = PrivateClassFoo.class.getDeclaredConstructor();
        con.setAccessible(true);
        PrivateClassFoo privateClassFoo = con.newInstance();
        privateClassFoo.hello();

    }

    @Test
    public void Test12() throws InstantiationException {
        PrivateClassFoo privateClassFoo = (PrivateClassFoo) UnsafeUtils.unsafe.allocateInstance(PrivateClassFoo.class);
        // 不会触发构造方法
        privateClassFoo.hello();

    }
}
