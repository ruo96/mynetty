package com.wrh.utils.jsonconverter;

import net.sf.json.JSONObject;

/**
 * @author wuruohong
 * @Classname JsonLibUtil
 * @Description TODO
 * @Date 2020/7/3 20:18
 */
public class JsonLibUtil {
    public static String bean2Json(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
    }
}
