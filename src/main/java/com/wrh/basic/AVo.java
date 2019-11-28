package com.wrh.basic;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:27 2019/11/12 0012
 * @Modified By:
 */
@Data
public class AVo {

    @Value("${key}")
    private String key;

    private int num;

    private String name;

    private String hashCodeName;

    public void setHash(){
        System.out.println("=== key is : " + key);
        this.hashCodeName = "wrh"+key;
    }
}
