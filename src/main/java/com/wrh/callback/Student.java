package com.wrh.callback;

import lombok.Data;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:16 2018/7/16 0016
 * @Modified By:
 */
@Data
public class Student {

    private String name = null;
    public Student(String name){
        this.name = name;
    }

    private int calcADD(int a , int b){
        return a+b;
    }

    /*这个就是*/
    public void fillBlank(int a , int b){
        int result = calcADD(a,b);
        System.out.println(name + "心算:"+a + "+"+b+"="+result);
    }
}
