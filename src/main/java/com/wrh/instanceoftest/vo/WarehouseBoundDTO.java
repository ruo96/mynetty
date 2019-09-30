package com.wrh.instanceoftest.vo;

import lombok.Data;

import java.util.HashSet;

/**
 * Created By @author Zing @date 2018/10/22 10:44
 */
@Data
public class WarehouseBoundDTO {


    private String name;

    /**
     * 关联列表
     */
    private HashSet<BoundDTO> connectionSet;



}
