package com.wrh.functionInterfaceTest;

import lombok.Data;

import java.io.Serializable;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 11:39 2019/10/15 0015
 * @Modified By:
 */
@Data
public class Student  {

    private static final long serialVersionUID = 3691209181051027992L;

    private String name;

    private int age;

    private int stature;

    public Student(String name,int age,int stature){
        this.name = name;
        this.age =age;
        this.stature = stature;
    }
}
