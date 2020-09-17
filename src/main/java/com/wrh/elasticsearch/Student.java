package com.wrh.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:46 2019/4/25 0025
 * @Modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String name;
    private Integer id;
    private Integer grade;

    private Long money;

    private String title;

    private boolean flag = true;


}
