package com.wrh.reflection;

import com.wrh.elasticsearch.Student;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:25 2019/1/7 0007
 * @Modified By:
 */
public class TestReflection {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> class1 = null;
        Class<?> class2 = null;
        Class<?> class3 = null;
        // 一般采用这种形式
        class1 = Class.forName("com.wrh.reflection.TestReflection");
        class2 = new TestReflection().getClass();
        class3 = TestReflection.class;
        System.out.println("类名称   " + class1.getName());
        System.out.println("类名称   " + class2.getName());
        System.out.println("类名称   " + class3.getName());
    }


    @Test
    public void Test25() throws ClassNotFoundException {
        Class clz = Class.forName("com.wrh.elasticsearch.Student");
        System.out.println(clz.getClassLoader());
        System.out.println(clz.getName());
        System.out.println(Arrays.toString(clz.getDeclaredFields()));
        System.out.println(clz.getSuperclass());
        System.out.println(Arrays.toString(clz.getInterfaces()));
        System.out.println(Arrays.toString(clz.getMethods()));
//        本内置类型的包装类，都有一个Type属性
        Class c = Integer.TYPE;

    }
    @Test
    public void Test44() {
        int a =0;
        System.out.println("a = " + a);
        System.out.println("a = " + a);

    }
    
    @Test
    public void Test52() throws NoSuchFieldException, IllegalAccessException {
        ReflectPointer rp1 = new ReflectPointer(3,4);
        Field fieldx = rp1.getClass().getField("x");//必须是x或者y
        System.out.println(fieldx.get(rp1));


        /*
         * private的成员变量必须使用getDeclaredField，并setAccessible(true),否则看得到拿不到
         */
        Field fieldy = rp1.getClass().getDeclaredField("y");
        fieldy.setAccessible(true);//暴力反射
        System.out.println(fieldy.get(rp1));
    }
    
    @Test
    public void Test68() throws Exception {
        ReflectPointer rp1 = new ReflectPointer(3,4);
        System.out.println(rp1);
        changeBtoA(rp1);
        System.out.println(rp1);

    }
    private static void changeBtoA(Object obj) throws RuntimeException, Exception {
        Field[] fields = obj.getClass().getFields();

        for(Field field : fields) {
            //if(field.getType().equals(String.class))
            //由于字节码只有一份,用equals语义不准确
            if(field.getType()==String.class) {
                String oldValue = (String)field.get(obj);
                String newValue = oldValue.replace('b', 'a');
                field.set(obj,newValue);
            }
        }
    }
    
    @Test
    public void Test90() {
        try {
            Class cls = Class.forName("com.wrh.reflection.S");
            boolean b1 = cls.isInstance(new Integer(37));
            System.out.println(b1);
            boolean b2 = cls.isInstance(new S());
            System.out.println(b2);
        }
        catch (Throwable e) {
            System.err.println(e);
        }

    }
    
    @Test
    public void Test105() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String str = "shfsfs";
        //包开头是com表示是sun内部用的，java打头的才是用户的
        Method mtCharAt = String.class.getMethod("charAt", int.class);
        Object ch = mtCharAt.invoke(str,1);//若第一个参数是null，则肯定是静态方法
        System.out.println(ch);

        System.out.println(mtCharAt.invoke(str, new Object[]{2}));//1.4语法
    }
    
    @Test
    public void Test118() {
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
