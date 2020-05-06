package com.wrh.gson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 9:59 2019/2/20 0020
 * @Modified By:
 */
public class TestGson {
    private Object Map;

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
    
    @Test
    public void Test() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String str = "{\"sex\":null,\"name\":\"mafly\",\"age\":\"18\"}";
        for (int i = 0; i < 1000000; i++) {

            Map<String, String> map = new Gson().fromJson(str,
                    Map.class);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());

        stopWatch.start();
        for (int i = 0; i < 1000000; i++) {
            JSONObject map = JSON.parseObject(str);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
    }
}
