package com.wrh.enums;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:07 2019/11/21 0021
 * @Modified By:
 */
public enum FieldEnum {

    CITY_CODE("CITY_CODE","市行政区划代码"),
    AREA_CODE("AREA_CODE","区县行政区划代码"),
    BREEDING_ID("BREEDING_ID","养殖企业备案编码"),
    BREEDING_NAME("BREEDING_NAME","养殖企业备案名称"),
    BATCH_ID("BATCH_ID","养殖批次号"),

    BREEDING_DATE("BREEDING_DATE","养殖日期"),
    PRINCIPAL_ID("PRINCIPAL_ID","负责人身份证号"),
    PRINCIPAL_NAME("PRINCIPAL_NAME","负责人姓名"),
    PROPORTION_AMOUNT("PROPORTION_AMOUNT","养殖规模大小"),
    PROPROTION_UNIT("PROPROTION_UNIT","养殖规模单位"),
    VARIETY_CODE("VARIETY_CODE","品种编码"),
    VARIETY_NAME("VARIETY_NAME","品种名称"),

    PURCHASE_BATCHID("PURCHASE_BATCHID","投入品采购批次号"),
    INPUTS_TYPE("INPUTS_TYPE","投入品种类"),
    INPUTS_DATE("INPUTS_DATE","投入日期"),
    VARIETY_CODE1("VARIETY_CODE","投入品编码");

    private String key;
    private String val;

    FieldEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public String key(){
        return key;
    }

    public String val(){
        return val;
    }

    public static FieldEnum getByKey(String key){
        for(FieldEnum fieldEnum : values()){
            if(fieldEnum.key.equals(key)){
                return fieldEnum;
            }
        }
        return null;
    }

    public static FieldEnum getByVal(String val){
        for(FieldEnum fieldEnum : values()){
            if(fieldEnum.val.equals(val)){
                return fieldEnum;
            }
        }
        return null;
    }
}
