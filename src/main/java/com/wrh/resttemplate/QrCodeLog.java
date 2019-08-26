package com.wrh.resttemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author zing
 * @date 2018/2/2 11:33
 */
@Data
public class QrCodeLog {
    /**
     * '日志ID'
     */
    private Integer autoId;
    /**
     * '用户ID'
     */
    private String userId;
    /**
     * '用户手机'
     */
    private String userPhone;
    /**
     * 扫码类型 码类型，0溯码，1唯码
     */
    private String scanType;
    /**
     * '码ID'
     */
    private String qrCodeId;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String area;

    /**
     * 国家
     */
    private String country;

    /**
     * 经度
     */
    private double longitude;
    /**
     * 纬度
     */
    private double latitude;
    /**
     * 百度的经度
     */
    private double bdLongitude;
    /**
     * 百度的纬度
     */
    private double bdLatitude;
    /**
     * '二维码内容'
     */
    private String qrCode;
    /**
     * '扫描者IP地址'
     */
    private String userIp;
    /**
     * '扫描者地理位置'
     */
    private String userAddress;
    /**
     * '创建者'
     */
    private String logCreator;
    /**
     * '创建时间'
     */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @JSONField(format = "YYYY-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * '更新者'
     */
    private String logUpdater;
    /**
     * '更新时间'
     */
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    @JSONField(format = "YYYY-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 登录的Token
     */
    private transient String grantToken;

    public JSONObject toJsonObject() {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(this));
        return jsonObject;
    }
}
