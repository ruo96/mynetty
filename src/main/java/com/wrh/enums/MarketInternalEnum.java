package com.wrh.enums;


import java.util.ArrayList;
import java.util.List;

/**
 * @author wuruohong
 * @Classname MarketEnum
 * @Description 市场类别
 * @Date 2020/12/11 15:08
 */
public enum MarketInternalEnum {

    TOTAL("国内全部",1),
    JOINT("国内联运",2),
    SOLO("国内独代",3);

    private String key;

    private Integer value;

    MarketInternalEnum(String key, Integer value) {
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
        for (MarketInternalEnum marketEnum : MarketInternalEnum.values()) {
            result.add(marketEnum.key);
        }
        return result;
    }

    public static MarketInternalEnum findEnumByKey(String key) {
        for (MarketInternalEnum marketEnum : MarketInternalEnum.values()) {
            if (marketEnum.getKey().equals(key)) {
                return marketEnum;
            }
        }
        return null;
    }

    public static MarketInternalEnum findEnumByValue(Integer value) {
        for (MarketInternalEnum marketEnum : MarketInternalEnum.values()) {
            if (marketEnum.getValue().equals(value)) {
                return marketEnum;
            }
        }
        return null;
    }

}
