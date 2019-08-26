package com.wrh.resttemplate.testmethod;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:53 2019/6/21 0021
 * @Modified By:
 */
public class TestNewGoogle {
    public static String getLngLat(String address) {
        /*String code = HttpClientUtil.doGet("https://ditu.google.cn/maps/api/geocode/json?address=" + address + "&key=你的ak");

        RestTemplate restTemplate = new RestTemplate();

        String code =

        com.alibaba.fastjson.JSONObject jsonObjectOri = com.alibaba.fastjson.JSONObject.parseObject(code);
        // 获取解析状态
        String status = jsonObjectOri.getString("status");
         if (status == "OK" || "OK".equals(status)) {
             // 解析的地址不为空时 进行值的获取
             JSONObject results = (JSONObject) jsonObjectOri.getJSONArray("results").get(0);
             JSONObject geometry = JSONObject.parseObject(results.getString("geometry"));
             JSONObject lng_lat = JSONObject.parseObject(geometry.getString("location"));
             String lng = lng_lat.getString("lng");
             String lat = lng_lat.getString("lat");
             String lngLat = lat + "," + lng; return "获取成功！"+address+"的经纬度信息为："+lngLat;
         }*/
         return "获取失败！";
    }

    public static void main(String[] args) {

    }


}
