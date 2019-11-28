package com.wrh.TimeCalculate;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 2:01 2019/11/13 0013
 * @Modified By:
 */
@Data
public class CqTraceUpload  {

//    private static final long serialVersionUID = 1L;

    @JSONField(name = "BIZ_TYPE")
    private String BIZ_TYPE;

    @JSONField(name = "REQ_TIME")
    private String REQ_TIME;

    @JSONField(name = "REQ_ID")
    private String REQ_ID;

    @JSONField(name = "AUTH_ID")
    private String AUTH_ID;

    @JSONField(name = "PARAM")
    private Object PARAM;

}

