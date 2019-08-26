package com.wrh.aes;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 9:26 2019/7/26 0026
 * @Modified By:
 */
@Slf4j
public class TestString {

    //查询溯源信息
    public static final String TRACING_TREE ="/zcs_gateway/apps/c/api/tracing/tree";

    /**微信添加扫码记录*/
    public static final String WX_SCAN_RECORD ="/zcs_gateway/apps/c/api/tracing";
    /**
     * 增加扫码次数
     */
    public static final String ADD_QRCODE_RECODE ="/zcs_gateway/apps/c/api/add/record";

    public static void main(String[] args) {
        String url = "/zcs_gateway/apps/c/api/scanned/code/info";
        System.out.println(StringUtils.startsWithAny(url,
                new String[]{TRACING_TREE,
                        WX_SCAN_RECORD,
                        ADD_QRCODE_RECODE}));

        log.info("hello");
    }
}
