package com.wrh.resttemplate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:47 2019/6/21 0021
 * @Modified By:
 */
public class TestRestemplate {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        QrCodeLog log = new QrCodeLog();
        log.setLatitude(45.123123);
        log.setLongitude(45.894809);
        Map<String,String> map = new HashMap<>();


        ResponseEntity<GoogleMapBean> responseEntity = null;



//        &sensor=true&language=zh-CN
        try {
//            responseEntity = restTemplate.getForEntity("http://13.58.247.204/maps/api/geocode/json?latlng=" + log.getLatitude() + ","
//                    + log.getLongitude() + "&key=AIzaSyB8DBBp7tItLTCG-c3z4ZnQs6sM-kCk8mQ", GoogleMapBean.class);
            responseEntity = restTemplate.getForEntity("http://maps.google.cn/maps/api/geocode/json?latlng=" + log.getLatitude() + ","
                    + log.getLongitude() +  "&key=AIzaSyB8DBBp7tItLTCG-c3z4ZnQs6sM-kCk8mQ", GoogleMapBean.class);
        } catch (RestClientException e) {
            System.out.println("异常：{}" + e);
        }
        if (responseEntity != null) {
            if (CollectionUtils.isEmpty(responseEntity.getBody().getResults())) {
                System.out.println("country" + "地址未知");
            }
            for (Address_components address_components : responseEntity.getBody().getResults().get(0).getAddress_components()) {
                if (address_components.getTypes().contains("sublocality")) {
                    //区
                    map.put("area", trimToEmpty(address_components.getLong_name()));
                    System.out.println();
                }
                if (address_components.getTypes().contains("locality")) {
                    //城市
                    map.put("city", address_components.getLong_name());
                }
                if (address_components.getTypes().contains("administrative_area_level_1")) {
                    //省份
                    map.put("province", address_components.getLong_name());
                }
                if (address_components.getTypes().contains("country")) {
                    map.put("country", address_components.getLong_name());
                }
            }
            //详细地址
            map.put("userAddress", responseEntity.getBody().getResults().get(0).getFormatted_address());

        }else {
            System.out.println("返回为空");
        }

        System.out.println("map: " + map);
    }

    private static String trimToEmpty(String str) {
        return StringUtils.isEmpty(str) ? "" : str;
    }
}
