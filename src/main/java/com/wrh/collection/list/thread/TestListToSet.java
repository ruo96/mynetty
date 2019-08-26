package com.wrh.collection.list.thread;

import com.wrh.elasticsearch.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:11 2019/5/16 0016
 * @Modified By:
 */
public class TestListToSet {
    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setName("wrh");
        student1.setId(0);

        Student student2 = new Student();
        student2.setName("wrh");
        student2.setId(0);




        List<Student> a = new ArrayList<>();
        a.add(student1);
        a.add(student2);

        Set<Student> b = new HashSet<>(a);
        System.out.println("a size: " + a.size());
        System.out.println(b);
        System.out.println(b.size());
    }
}
