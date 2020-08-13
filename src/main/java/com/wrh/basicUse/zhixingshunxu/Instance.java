package com.wrh.basicUse.zhixingshunxu;

import lombok.Data;

/**
 * @author wuruohong
 * @Classname Instance
 * @Description TODO
 * @Date 2020/7/2 11:41
 */
@Data
public class Instance {
    private String value;

    public Instance(){

    }

    public Instance(String value){
        System.out.println(value);
        this.value = value;
    }
}
