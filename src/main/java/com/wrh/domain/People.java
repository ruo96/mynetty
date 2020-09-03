package com.wrh.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuruohong
 * @Classname People
 * @Description TODO
 * @Date 2020/9/2 16:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class People {
    private int age;

    private String name;
}
