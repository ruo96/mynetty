package com.wrh.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.wrh.elasticsearch.Student;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:00 2019/6/12 0012
 * @Modified By:
 */
public class TestFastJson {
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();

        Data data = new Data();
        data.setAccount("xy04");
        data.setPassword("12345678");
        data.setFlag(0);
        data.setDeviceId("869161025957341");


//        String str1 = "{\"account\":\"xy04\",\"deviceId\":\"869161025957341\",\"flag\":0,\"password\":\"12345678\"}";

        Data1 data1 = new Data1();
        data1.setData(JSON.toJSONString(data));
        data1.setTimestamp("456");


        /*obj.put("data",);
        obj.put("timestamp","");*/
        String str = JSON.toJSONString(data1);
        System.out.println(str);
    }
}
