package com.wrh.collection.list;

import com.wrh.elasticsearch.Student;
import com.wrh.list.TestList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wuruohong
 * @Classname TestSortList
 * @Description TODO
 * @Date 2021/12/20 15:30
 */
public class TestSortList {
    @Test
    public void Test11() {
        List<Student> list = new ArrayList<>();
        list = TestList.getStudentList();
        System.out.println("list = " + list);

        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getId().compareTo(o1.getId());
            }
        });
        System.out.println("list = " + list);

    }

    @Test
    public void Test36() {
        List<Student> list = new ArrayList<>();
        list = TestList.getStudentList();
        Student s = new Student();
        s.setId(null);
        s.setName("null");
        list.add(s);
        System.out.println("list = " + list);

        /** 防止出现某些比较字段为null*/
        list = list.stream()
                .sorted(Comparator.comparing(Student::getId, Comparator.nullsFirst(Integer::compareTo)))
                .collect(Collectors.toList());
        System.out.println("list = " + list);

    }
}
