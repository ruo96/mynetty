package com.wrh.controller.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wuruohong
 * @Classname User
 * @Description TODO
 * @Date 2020/10/20 11:25
 */
@Data
@AllArgsConstructor
public class User {
    private String userId;
    private String userName;
    private LocalDateTime createTime;
}
