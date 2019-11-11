package com.wrh.functionInterfaceTest;

import lombok.Data;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 11:39 2019/10/15 0015
 * @Modified By:
 */
@Data
public class Student {
    private String name;

    private int age;

    private int stature;

    Student(String name,int age,int stature){
        this.name = name;
        this.age =age;
        this.stature = stature;
    }
}
