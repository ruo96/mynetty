package com.wrh.enums;


import java.util.ArrayList;
import java.util.List;

/**
 * @author wuruohong
 * @Classname MarketEnum
 * @Description 市场类别
 * @Date 2020/12/11 15:08
 */
public enum MarketOverseaEnum {

    TOTAL("全部游戏",0),
    CITY_2("国内全部",1),
    CITY_3("国内联运",2),
    CITY_4("国内独代",3);

    private String key;

    private Integer value;

    MarketOverseaEnum(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public static List<String> getAllKeys() {
        List<String> result = new ArrayList<>();
        for (MarketOverseaEnum marketEnum : MarketOverseaEnum.values()) {
            result.add(marketEnum.key);
        }
        return result;
    }

    public static MarketOverseaEnum findEnumByKey(String key) {
        for (MarketOverseaEnum marketEnum : MarketOverseaEnum.values()) {
            if (marketEnum.getKey().equals(key)) {
                return marketEnum;
            }
        }
        return null;
    }

    public static MarketOverseaEnum findEnumByValue(Integer value) {
        for (MarketOverseaEnum marketEnum : MarketOverseaEnum.values()) {
            if (marketEnum.getValue().equals(value)) {
                return marketEnum;
            }
        }
        return null;
    }
}
