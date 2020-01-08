package com.wrh.big.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname TestUtils
 * @Description TODO
 * @Date 2020/1/8 14:58
 * @Created by wuruohong
 */
@Slf4j
public class TestUtils {

    private static final String baseUrl = "http://game-sdk-info-service.bilibili.co";
    private static final String paraPath = "/v1/sdk/get_all_tour_limit_config";
    private static final String appSecret = "60a6aaa964ce4deea7d9e0acac10fe75";
    private static final String appKey="3ab9fe452684a3b051";
    private static final String USER_AGENT         = "Mozilla/5.0 BSGameSDK";
    private static final String CONTENT_TYPE       = "Content-type";


    @Test
    public void Test() throws Exception {
        List<NameValuePair> nvps = new ArrayList<>();

        String timestamp = String.valueOf(System.currentTimeMillis());
        nvps.add(new BasicNameValuePair("timestamp", timestamp));
        nvps.add(new BasicNameValuePair("appKey", appKey));

        String sign = TourConfigSignHelper.getSign(nvps, appSecret);
        log.info("===> timestamp:{}",timestamp);
        log.info("===> appKey:{}",appKey);
        log.info("===> sign:{}",sign);
        nvps.add(new BasicNameValuePair("sign", TourConfigSignHelper.getSign(nvps, appSecret)));

        InetAddress address = InetAddress.getLocalHost();
        String hostAddress = address.getHostAddress();
        Header[] headers = getRequestHeaderArray(hostAddress);

        HttpApiClient httpApiClient = new HttpApiClient();
        httpApiClient.afterPropertiesSet();
        log.info(">>> httpApiClient :{}",httpApiClient.getConnectTimeout());
        log.info(">>> httpApiClient :{}",httpApiClient.getClient());
        log.info(">>> httpApiClient :{}",httpApiClient.getConnectTimeout());

        log.info("headers >>> {}", JSON.toJSONString(headers));

        String responseStr = httpApiClient.get(baseUrl, paraPath, nvps, headers);

        log.info(">>> result: {}",responseStr);

    }





    private Header[] getRequestHeaderArray(String clientIP) {
        Header[] headerArray = new Header[3];
        headerArray[0] = new BasicHeader("User-Agent", USER_AGENT);
        headerArray[1] = new BasicHeader("Content-type", CONTENT_TYPE);
        headerArray[2] = new BasicHeader("X-BACKEND-BILI-REAL-IP", clientIP);
        return headerArray;
    }
}
