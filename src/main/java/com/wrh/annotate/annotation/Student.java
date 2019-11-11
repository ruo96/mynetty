package com.wrh.annotate.annotation;

import lombok.Data;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:38 2019/11/11 0011
 * @Modified By:
 */
@Data
public class Student {

    @Odd
    private int age;

    private String name;
}
