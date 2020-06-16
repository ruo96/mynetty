package com.wrh.collection.list;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;


@Data
@JsonNaming(PropertyNamingStrategy.PropertyNamingStrategyBase.class)
public class GameTopOffline {

    /** 游戏基础id*/
    private Integer gameBaseId;

    /** 离线总收入*/
    private Long income;
}
