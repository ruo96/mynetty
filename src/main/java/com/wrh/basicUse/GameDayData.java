package com.wrh.basicUse;

import lombok.Data;

import java.util.Date;


@Data
public class GameDayData {

    /**统计日期*/
    private Date ds;

    /**是否包含游客   0 不包含   1 包含*/
    private Integer withTourist;

    /**新增用户*/
    private Integer newAccCnt;

    /**新增设备*/
    private Integer newDeviceCnt;

    /**有效新增用户  dc_src_game_day_effective*/
    private Integer effNewAccCnt;

    /**有效新增设备 dc_src_game_day_effective*/
    private Integer effNewDeviceCnt;

    /**活跃用户*/
    private Integer activeAccCnt;

    /**活跃设备*/
    private Integer activeDeviceCnt;

    /**有效活跃用户 dc_src_game_day_effective*/
    private Integer effActiveAccCnt;

    /**有效活跃设备 dc_src_game_day_effective*/
    private Integer effActiveDeviceCnt;

    /**次留*/
//    private double remain;

    /**设备次留*/
//    private double remainDevice;


    /**付费*/
    private Long paySumMoney;

    /**付费账户*/
    private Integer payAccCnt;

    /**付费渗透率  pay_acc_cnt / active_acc_cnt*/
    private Double payRate;

    /**有效付费渗透率  pay_acc_cnt / eff_active_acc_cnt*/
    private Double effPayRate;
}
