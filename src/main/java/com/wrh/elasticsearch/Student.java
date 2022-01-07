package com.wrh.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:46 2019/4/25 0025
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

//    private String head = "all";

    private String name;
    private Integer id;
    private Integer grade;

    private Long money;

    private String title;

    private Boolean flag;


    @Override
    public boolean equals(Object obj) {
        return (this.hashCode() == obj.hashCode());
    }

    public static int compareByNameThenGrade(Student s1, Student s2) {
        if (s1.name.equals(s2.name)) {
            return Integer.compare(s1.grade, s2.grade);
        } else {
            return s1.name.compareTo(s2.name);
        }
    }




}
