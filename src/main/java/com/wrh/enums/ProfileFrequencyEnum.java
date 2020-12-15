package com.wrh.enums;


/**
 * @author wuruohong
 * @Classname SexEnum
 * @Description TODO
 * @Date 2020/12/11 15:08
 */
public enum ProfileFrequencyEnum {

    LOW("低频"),
    MIDIEM("中频"),
    HIGH("高频");


    private String text;

    ProfileFrequencyEnum(String text) {
        this.text = text;
    }


    public String getText() {
        return text;
    }
}
