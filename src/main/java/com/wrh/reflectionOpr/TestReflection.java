package com.wrh.reflectionOpr;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

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
    
    @Test
    public void Test33() {
        // TODO Auto-generated method stub
        String str = "abc";
        Class cls1 = str.getClass();
        Class cls2 = String.class;
        Class cls3 = null;//必须要加上null
        try {
            cls3 = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(cls1==cls2);
        System.out.println(cls1==cls3);

        System.out.println("cls1.isPrimitive() = " + cls1.isPrimitive());
        System.out.println("int.class.isPrimitive() = " + int.class.isPrimitive());
        System.out.println("int.class == Integer.class = " + (int.class == Integer.class));
        System.out.println("int.class == Integer.TYPE = " + (int.class == Integer.TYPE));
        System.out.println("int[].class.isPrimitive() = " + int[].class.isPrimitive());
        System.out.println("int[].class.isArray() = " + int[].class.isArray());

    }

    @Test
    public void Test58() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String str = "shfsfs";
        //包开头是com表示是sun内部用的，java打头的才是用户的
        Method mtCharAt = String.class.getMethod("charAt", int.class);
        Object ch = mtCharAt.invoke(str,1);//若第一个参数是null，则肯定是静态方法
        System.out.println(ch);

        System.out.println(mtCharAt.invoke(str, new Object[]{2}));//1.4语法

    }
    
    @Test
    public void Test70() {
        int[] a = new int[3];
        int[] b = new int[]{4,5,5};//直接赋值后不可以指定长度，否则ＣＥ
        int[][] c = new int[3][2];
        String[] d = new String[]{"jjj","kkkk"};
        System.out.println(a==b);//false
        System.out.println(a.getClass()==b.getClass());//true
        //System.out.println(a.getClass()==d.getClass());    //比较字节码a和cd也没法比
        System.out.println(a.getClass());//输出class [I
        System.out.println(a.getClass().getName());//输出[I,中括号表示数组，I表示整数

        System.out.println(a.getClass().getSuperclass());//输出class java.lang.Object
        System.out.println(d.getClass().getSuperclass());//输出class java.lang.Object

        //由于父类都是Object,下面都是可以的
        Object obj1 = a;//不可是Object[]
        Object obj2 = b;
        Object[] obj3 = c;//基本类型的一位数组只可以当做Object，非得还可以当做Object[]
        Object obj4 = d;

        //注意asList处理int[]和String[]的区别
        System.out.println(Arrays.asList(b));//1.4没有可变参数，使用的是数组,[[I@1bc4459]
        System.out.println(Arrays.asList(d));//[jjj, kkkk]


    }
}
