/*

 */
package com.wrh.basicUse.vo;

import com.wrh.basicUse.Enum.CheckTypeEnum;
import com.wrh.basicUse.myannotation.CheckParam;
import lombok.Data;

import java.io.Serializable;

/**
 * 类OnlineCleanParamDto.java的实现描述：清洗层入参
 */
@Data
public class OnLineCleanParamDto implements Serializable {

    private static final long serialVersionUID = 3691209181051027992L;

    /** 游客标志 **/
    @CheckParam(checkTypeEunm = CheckTypeEnum.StringOnly)
    private String tourMark;
    
    /** 用户id **/
    @CheckParam(checkTypeEunm = CheckTypeEnum.StringOnly)
    private String uid;

    /** 设备号 **/
    @CheckParam(checkTypeEunm = CheckTypeEnum.StringOnly)
    private String buvid;

    /** 游戏id **/
    @CheckParam(checkTypeEunm = CheckTypeEnum.StringAndMustBeNum)
    private String gameId;

    /** 服务端接接收时间 **/
    @CheckParam(checkTypeEunm = CheckTypeEnum.StringOnly)
    private String serverRecTime;

    /** 心跳存活时间 **/
    @CheckParam(checkTypeEunm = CheckTypeEnum.StringOnly)
    private String intervalTime;

}
