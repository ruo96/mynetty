package com.wrh.list.vo;

import lombok.Data;

import java.util.Date;



@Data
public class TotalInfo {
    private String gameName;
    
    /** 游戏基础ID */
    private Integer gameBaseId;
    
    /** 日流水 */
    private Long paySumMoney;

    /** 日活跃用户 */
    private Integer activeAccCnt;

    /** 日有效活跃用户 */
    private Integer effActiveAccCnt;

    /** 日活跃设备 */
    private Integer activeDeviceCnt;

    /** 日有效新增用户 */
    private Integer effNewAccCnt;

    /** 日有效新增设备 */
    private Integer effNewDeviceCnt;

    /** 日付费用户 */
    private Integer payAccCnt;

    /** ARPPU */
    private String arppu;

    /** 日游客数 */
    private Integer touristCnt;

    /** 日游客转化数 */
    private Integer touristTransform1stDayCnt;

    /** 最高在线人数 */
    private Integer maxOnlineAccCnt;

    /** 平均在线人数 */
    private Integer avgOnlineAccCnt;

    /** 付费次数 */
    private Integer payBillCnt;
    
    private Date ds;
    
    private Integer newAccCnt;
    
    private Integer newDeviceCnt;
    
    private String fmtDs;

    /** 新增*/
    private Integer opStatusId;

    /** 图标*/
    private String icon;

    private Boolean haveRtRegMenu = Boolean.TRUE;

    private Boolean haveRtRechargeMenu = Boolean.TRUE;

    private Boolean haveRtOnlineMenu = Boolean.TRUE;

    private Long turnover;

    
}
