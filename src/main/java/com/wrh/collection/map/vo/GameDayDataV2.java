package com.wrh.collection.map.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;


/**
 * @Author: wuruohong
 * @Description:
 * @Date: Created in 2020-05-27
 * @Modified By:
 */
@Data
public class GameDayDataV2 {

    private String dsStr;

    /**统计日期*/
//    @JsonFormat(pattern = DATE_FORMAT, timezone = "GMT+8")
    private LocalDate ds;

    /** 游戏基础id*/
    private Integer gameBaseId;

    /**付费*/
    private Long paySumMoney;

    /**新增用户*/
    private Integer newAccCnt;

    /**有效新增用户  dc_src_game_day_effective_v2*/
    private Integer effNewAccCnt;

    /**活跃用户*/
    private Integer activeAccCnt;

    /**有效活跃用户 dc_src_game_day_effective_v2*/
    private Integer effActiveAccCnt;

    /**有效次留*/
    private Integer effRemainAcc2ndDayCnt;

    /**有效3留*/
    private Integer effRemainAcc3rdDayCnt;

    /**有效7留*/
    private Integer effRemainAcc7thDayCnt;

    /**有效14留*/
    private Integer effRemainAcc14thDayCnt;

    /**有效30留*/
    private Integer effRemainAcc30thDayCnt;

    /**新增用户第7日累计充值 计算lvt时候， lvt7 使用该字段 / newAccCnt  产品定义*/
    private Integer paySumMoneyNewAcc7d;

    /**新增用户第14日累计充值 计算lvt时候， lvt14 使用该字段 / newAccCnt  产品定义*/
    private Integer paySumMoneyNewAcc14d;

    /**新增用户第30日累计充值 计算lvt时候， lvt30 使用该字段 / newAccCnt  产品定义*/
    private Integer paySumMoneyNewAcc30d;

}
