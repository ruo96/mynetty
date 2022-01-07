package com.wrh.jdk;


import com.wrh.aop.RateLimitAop;
import com.wrh.aop.jdk.MoneyCountInvocationHandler;
import com.wrh.elasticsearch.Student;
import com.wrh.list.TestList;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuruohong
 * @Classname TestZidai
 * @Description TODO
 * @Date 2022/1/4 19:28
 */
public class TestZidai {

    @Test
    public void Test15() {
        System.out.println("StringUtils.isEmpty(\" \") = " + StringUtils.isEmpty(" "));
        System.out.println("StringUtils.endsWithIgnoreCase(\"hello\",\"lo\") = " + StringUtils.endsWithIgnoreCase("hello", "lo"));

        System.out.println("StringUtils.countMatches(\"helloworld\",\"o\") = " + StringUtils.countMatches("helloworld", "o"));
    }

    @Test
    public void Test24() {
        List<String> list = new ArrayList<>();
        list.add("w1");
        list.add("w1");

        System.out.println("CollectionUtils.hasUniqueObject(list) = " + CollectionUtils.hasUniqueObject(list));

        System.out.println("CollectionUtils.findValueOfType(list, String.class) = " + CollectionUtils.findValueOfType(list, String.class));

    }

    @Test
    public void Test40() throws IOException {
        FileCopyUtils.copy(new File("E:\\file\\data.txt"), new File("E:\\file\\data_copy.txt"));

    }

    @Test
    public void Test49() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath: //data.txt");
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());

    }

    @Test
    public void Test58() throws InvocationTargetException, IllegalAccessException {
        TestList l = new TestList();
        Method method = ReflectionUtils.findMethod(TestList.class, "getStudentList");
        List<Student> list = (List<Student>) method.invoke(l, null);
        System.out.println("list = " + list);

    }

    @Test
    public void Test72() {
        System.out.println("AopUtils.isJdkDynamicProxy(TestList.class) = " + AopUtils.isJdkDynamicProxy(TestList.class));
        System.out.println("AopUtils.isJdkDynamicProxy(RateLimitAop.class) = " + AopUtils.isJdkDynamicProxy(RateLimitAop.class));
        System.out.println("AopUtils.isJdkDynamicProxy(MoneyCountInvocationHandler.class) = " + AopUtils.isJdkDynamicProxy(MoneyCountInvocationHandler.class));

        System.out.println("Thread.currentThread().getStackTrace()[0].getClassName() = " + Thread.currentThread().getStackTrace()[0].getClassName());
        System.out.println("Thread.currentThread().getStackTrace()[1].getClassName() = " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("Thread.currentThread().getStackTrace()[2].getClassName() = " + Thread.currentThread().getStackTrace()[2].getClassName());

    }

}
