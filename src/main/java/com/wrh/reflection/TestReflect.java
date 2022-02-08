package com.wrh.reflection;

import org.junit.Test;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuruohong
 * @Classname TestReflect
 * @Description TODO
 * @Date 2021/4/23 11:59
 */
public class TestReflect {
    // com.wrh.reflection.Inter
    public static void main(String[] args) throws SecurityException, NoSuchMethodException, NoSuchFieldException, IllegalArgumentException, Exception {
        String str = args[0];
        /*
         * 这样会数组角标越界，因为压根没有这个字符数组
         * 需要右键在run as-configurations-arguments里输入b.Inter（完整类名）
         *
         */
        Method m = Class.forName(str).getMethod("main",String[].class);
        //下面这两种方式都可以,main方法需要一个参数

        m.invoke(null, new Object[]{new String[]{"111","222","333"}});
        m.invoke(null, (Object)new String[]{"111","222","333"});//这个可以说明，数组也是Object
        /*
         * m.invoke(null, new String[]{"111","222","333"})
         * 上面的不可以,因为java会自动拆包
         */
    }

    @Test
    public void Test32() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.getClass().getMethod("add", Object.class).invoke(list, 333);
        //System.out.println("list = " + list);
        //System.out.println("list.get(0).getClass().getName() = " + list.get(0).getClass().getName());
        //System.out.println("list.get(1).getClass() = " + list.get(1).getClass());
    }

    public List<String> list1;

    /**
     * 既然擦除了类型，为什么在运行期仍能反射获得类型？
     * 答案就藏在 class 文件中
     *
     * 通过反射，我确实获得了 list 的类型。那既然类型被擦除了，这又是怎么做到的呢？
     * class 文件里面存了这个信息，所以我们通过反射自然而然的就能得到这个类型。没错，就是这么简单。
     *
     * 也正因为原理如此，所以我们只能对以下三种情况利用反射获取泛型类型：
     *
     * 成员变量的泛型
     * 方法入参的泛型
     * 方法返回值的泛型
     */
    @Test
    public void Test47() throws NoSuchFieldException {
        Field field = TestReflect.class.getField("list1");
        Type type1 = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        System.out.println("type1 = " + type1);

    }

    @Test
    public void Test69() throws InvocationTargetException, IllegalAccessException {
        Reflect reflect = new Reflect("a");

        Method[] methods = Reflect.class.getMethods();
        Field[] fields = Reflect.class.getDeclaredFields();

        for (int i = 0; i < fields.length; i ++) {
            //fields[i].setAccessible(true);
            System.out.println(fields[i].getName());
        }

        System.out.println("===================");

        for (int j = 2; j < methods.length; j ++) {
            methods[j].setAccessible(true);
            System.out.println(methods[j].getName());

            methods[j].invoke(reflect);
            System.out.println(methods[j].getName());
        }

    }
}
