package com.wrh.list.vo;

import lombok.Data;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/9/2 23:58
 */
@Data
public class DataList {
    private String name;
    private Integer age;

    public DataList(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
