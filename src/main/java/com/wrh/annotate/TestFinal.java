package com.wrh.annotate;

import com.wrh.elasticsearch.Student;
import com.wrh.list.TestList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:21 2018/10/17 0017
 * @Modified By:
 */

@Slf4j
public class TestFinal {

    /*@Autowired
    public TestAnaotate.info testA;*/


    public void tstAno(){
        System.out.println("classloader is : "+ Thread.currentThread().getContextClassLoader());

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                log.info("classloader1 is : {}",Thread.currentThread().getContextClassLoader());
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                log.info("classloader2 is : {}",Thread.currentThread().getContextClassLoader());
            }
        };

        thread1.start();
        thread2.start();

    }

    @Test
    public void Test42() {
        List<Student> list = TestList.getStudentList();
        list = Collections.unmodifiableList(list);
        System.out.println("list = " + list);
        Student s = new Student();
        s.setName("ww");
        list.add(s);  // 编译不会报错，但是运行会
        System.out.println("list = " + list);


    }
}
