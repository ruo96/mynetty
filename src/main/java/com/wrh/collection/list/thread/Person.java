package com.wrh.collection.list.thread;

import lombok.Data;

import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:46 2019/5/16 0016
 * @Modified By:
 */
@Data
public class Person {
    private String name;

    private List<Car> cars;
}
