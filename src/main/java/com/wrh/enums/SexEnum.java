package com.wrh.enums;

import lombok.Data;

/**
 * @author wuruohong
 * @Classname SexEnum
 * @Description TODO
 * @Date 2020/12/11 15:08
 */
public enum SexEnum implements Seasons{
    MALE(1,"男"),
    FEMALE(2,"女"),
    UNKONWN(-1,"未知");
    
    private Integer key;
    
    private String text;

    SexEnum(Integer key, String text) {
        this.key = key;
        this.text = text;
    }

    public Integer getKey() {
        return key;
    }

    public String getText() {
        return text;
    }



}
