package com.wrh.sign;

import com.wrh.utils.ApiSignHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Classname TestSign
 * @Description TODO
 * @Date 2020/1/14 19:21
 * @Created by wuruohong
 */
@Slf4j
public class TestSign {

    @Test
    public void Test() throws UnsupportedEncodingException {

        long timestamp = System.currentTimeMillis();
        String appKey = "76f573";
        String secretKey = "636bb59dd8074d6f991a7a460e76f573";
        Map<String,String[]> map = new TreeMap<>();
        map.put("ts",new String[]{String.valueOf(timestamp)});
        map.put("appKey",new String[]{appKey});

        TreeMap<String,String> map1 = new TreeMap<>();
        map1.put("ts",String.valueOf(timestamp));
        map1.put("appKey",appKey);
//        ApiSignHelper.getSign(map, appKey);
        String sign = ApiSignHelper.getSign(map1, secretKey);
        log.info("===> sign: {}",sign);
        log.info("===> timestamp: {}",timestamp);
    }
}
