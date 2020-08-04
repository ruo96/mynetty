package com.wrh.controller.intercept;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


/**
 * @author zing
 * @date 2018/7/4 11:19
 */
@Data
@Slf4j
public class RequestInfo<T> {
    /**
     * 请求参数
     */
    protected T data;
    /**
     * 请求时间戳
     */
    protected long timestamp;
    /**
     * 登录之后有Token，未登录则为空
     */
    protected String token = StringUtils.EMPTY;
    /**
     * 请求数据放篡改校验标记
     */
    protected String digest;

    /**
     * 计算摘要
     *
     * @return
     */
    public String makeDigest() {
        StringBuilder builder = new StringBuilder(JSON.toJSONString(data));
//        StringBuilder builder = new StringBuilder(new String(Base64.getDecoder().decode((byte[])data)));
        builder.append(timestamp).append(token);
        return Digest.md5TrimZero(builder.toString());
    }

}
