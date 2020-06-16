package com.wrh.list.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class TotalYearKpi implements Serializable {
    private static final long serialVersionUID = 1L;

    /**自增id*/
    private Integer id;

    /**游戏运营类别id*/
    private Integer groupId;

    /**当日收入 单位分*/
    private Long money;

}
