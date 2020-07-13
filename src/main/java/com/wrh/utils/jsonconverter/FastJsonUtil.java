package com.wrh.utils.jsonconverter;

import com.alibaba.fastjson.JSON;

/**
 * @author wuruohong
 * @Classname FastJsonUtil
 * @Description TODO
 * @Date 2020/7/3 20:15
 */
public class FastJsonUtil {
    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return JSON.parseObject(jsonStr, objClass);
    }
}
