package com.wrh.aop;

import com.wrh.elasticsearch.Student;
import javassist.*;

import java.lang.reflect.Method;

/**
 * @author wuruohong
 * @Classname JavaassistDemo
 * @Description TODO
 * @Date 2021/10/18 15:44
 */
public class JavaassistDemo {
    public static void main(String[] args) {
        makeNewClass();
    }

    public static Class<?> makeNewClass() {
        try {
            // 获取ClassPool
            ClassPool pool = ClassPool.getDefault();
            // 创建Student类
            CtClass ctClass = pool.makeClass("com.wrh.elasticsearch.Student");
            // 创建Student类成员变量name
            CtField name = new CtField(pool.get("java.lang.String"), "name", ctClass);
            // 设置name为私有
            name.setModifiers(Modifier.PRIVATE);
            // 将name写入class
            ctClass.addField(name, CtField.Initializer.constant("")); //写入class文件
            //增加set方法，名字为"setName"
            ctClass.addMethod(CtNewMethod.setter("setName", name));
            //增加get方法，名字为getname
            ctClass.addMethod(CtNewMethod.getter("getName", name));
            // 添加无参的构造体
            CtConstructor cons = new CtConstructor(new CtClass[] {}, ctClass);
            cons.setBody("{name = \"Brant\";}"); //相当于public Sclass(){this.name = "brant";}
            ctClass.addConstructor(cons);
            // 添加有参的构造体
            cons = new CtConstructor(new CtClass[] {pool.get("java.lang.String")}, ctClass);
            cons.setBody("{$0.name = $1;}");  //第一个传入的形参$1,第二个传入的形参$2，相当于public Sclass(String s){this.name = s;}
            ctClass.addConstructor(cons);

            //反射调用新创建的类
            Class<?> aClass =  ctClass .toClass();
            Object student = aClass.newInstance();
            Method getter = null;
            getter = student.getClass().getMethod("getName");
            System.out.println(getter.invoke(student));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
