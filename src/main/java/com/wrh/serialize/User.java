package com.wrh.serialize;

import lombok.Data;

import java.io.Serializable;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:38 2019/5/17 0017
 * @Modified By:
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
