package com.wrh.enums;

/**
 * @author wuruohong
 * @Classname SeasonEnum
 * @Description TODO
 * @Date 2020/6/22 16:07
 */
public enum  SeasonEnum {
    SPRING(0),
    SUMMER(1),
    AUTUMN(2),
    WINTER(3);

    private int value;
    private SeasonEnum(Integer value){
        this.value = value;
    }
}
