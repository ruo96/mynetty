package com.wrh.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;



@Data
public class RealtimeReq {

    /** 游戏基础id*/
    @Min(value = -2,message = "gameBaseId wrong")
    @NotNull(message = "gameBaseId empty")
    private Integer gameBaseId;

    /** 日期*/
    @NotNull(message = "dsEnd empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dsEnd;

    /** 登录口径  1： SDK登录   2：区服登录*/
    @Min(value = 1,message = "loginType wrong")
    @Max(value = 2,message = "loginType wrong")
    @NotNull(message = "loginType empty")
    private Integer loginType;

    /** 是否包含游客  1：包含游客   0：不包含游客*/
    @Min(value = 0,message = "tourFlag wrong")
    @Max(value = 1,message = "tourFlag wrong")
    @NotNull(message = "tourFlag empty")
    private Integer tourFlag;

    /** sdk类型  */
    @Min(value = -1,message = "sdkTypeId wrong")
    @NotNull(message = "sdkTypeId empty")
    private Integer sdkTypeId;

    /** 是否实时  y：是   n：否*/
    @NotNull(message = "nowFlag empty")
    private String nowFlag;

    /** 日期列表*/
    private List<String> dsList;

    /** 游戏id列表*/
    private List<Integer> gameIdList;

    private List<Integer> allGameIdList;

    private int maxSlotNum;

    private int minSlotNum;

    private String ds;

}
