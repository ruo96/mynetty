package com.wrh.domain;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wuruohong
 * @Classname User
 * @Description TODO
 * @Date 2020/12/17 16:18
 */
@Data
public class User {
    private Integer id;

    @Size(min = 5,max = 10,message = "{user.name.size}",groups = ValidationGroup1.class)
    private String name;

    @NotNull(message = "{user.address.notnull}")
    private String address;

    @DecimalMin(value = "1",message = "{user.age.size}")
    @DecimalMax(value = "200",message = "{user.age.size}")
    private Integer age;

}
