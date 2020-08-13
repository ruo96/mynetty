package com.wrh.collection.map;

import lombok.Data;

import java.util.Date;


@Data
public class GameRealTimeData {
    private Date ds;
    private Integer slotNum;
    private Integer slideWindows;
    private Date startCountTime;
    private String x;
    private int y;
    private Integer totalNewAccCnt;
    private Integer currentNewAccCnt;
    private Integer todayNewAccCnt;
    private Integer totalPayMoney;
    private Integer currentPayMoney;
    private Integer todayPayMoney;
    private Integer zoneId;
    private String maxOnlineAccCnt;
    private String minOnlineAccCnt;
    private String currentOnlineAccCnt;
    private String maxActiveAccCnt;
    private String minActiveAccCnt;
    private String currentActiveAccCnt;
    private String fmtDs;
    private Integer gameBaseId;
    private Integer gameId;
    private Long turnover;
    
    public GameRealTimeData() {
    }
    
    public GameRealTimeData(Date ds) {
        this.ds = ds;
    }
    
    public GameRealTimeData(String fmtDs) {
        this.fmtDs = fmtDs;
    }
    
    public GameRealTimeData(Date ds, String fmtDs) {
        this.ds = ds;
        this.fmtDs = fmtDs;
    }
    
}
