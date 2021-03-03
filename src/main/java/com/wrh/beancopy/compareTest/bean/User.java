package com.wrh.beancopy.compareTest.bean;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.Date;

/**
 * @author wuruohong
 * @Classname User
 * @Description TODO
 * @Date 2021/1/21 16:44
 */
@Data
@Builder(toBuilder = true)
public class User {
    private long id;

    private int age;

    private String name;

    private boolean isMale;

    private School school;

    private Date createDate;

    public static User mock() {
        return User.builder()
                .id(RandomUtils.nextLong())
                .age(RandomUtils.nextInt())
                .name(RandomStringUtils.randomAlphanumeric(5))
                .isMale(RandomUtils.nextBoolean())
                .school(new School(RandomStringUtils.randomAlphanumeric(5), RandomUtils.nextInt()))
                .createDate(new Date())
                .build();
    }
}
