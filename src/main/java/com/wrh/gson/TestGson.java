package com.wrh.gson;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:59 2019/2/20 0020
 * @Modified By:
 */
public class TestGson {
    public static void main(String[] args) {
        HashMap<String,Object> map = new HashMap<>();

        map.put("name","mafly");
        map.put("age","18");
        map.put("sex",null);

        Gson gson = new Gson();
        String jsonString = gson.toJson(map);
        System.out.println(jsonString);

        String jsonStringNew = JSON.toJSONString(map);
        System.out.println(jsonStringNew);

        GsonBuilder gsonBuilder = new GsonBuilder();
        String jsonString1 = gsonBuilder.serializeNulls().create().toJson(map);
        System.out.println(jsonString1);
    }
}
