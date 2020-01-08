package com.wrh.big.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>签名工具类</p>
 *
 * @author luang on 2018/1/24.
 */
public class TourConfigSignHelper {

    /**
     * <p>按规则将参数转化为待签名字符串</p>
     */
    public static String getFormatString(Map<String, String> map) {

        if (map == null) {
            return "";
        }

        Optional<String> formatResult = map.entrySet().stream()
                .filter(
                        //1.排除sign字段
                        entry -> !"sign".equals(entry.getKey())
                )
                .sorted(
                        //2.按key升序排序
                        Comparator.comparing(Map.Entry::getKey)
                )
                .map(
                        //3.entry按规则映射为字符串
                        entry -> {
                            try {
                                return entry.getKey() + "=" + encodedFix(URLEncoder.encode(entry.getValue(), "UTF-8"));
                            } catch (UnsupportedEncodingException e) {
                                return "";
                            }
                        }
                )
                .reduce(
                        //4.按规则归纳所有字符串
                        (str1, str2) -> String.format("%s&%s", str1, str2)
                );

        return formatResult.orElse("");

    }

    private static String genSign(String content, String secret) {

        if (secret == null) {
            return "";
        }

        return DigestUtils.md5Hex(content.concat(secret));

    }

    public static String genSign(Map<String, String> paramMap, String secret) {

        return genSign(getFormatString(paramMap), secret);

    }

    public static String getSign(List<NameValuePair> nvps, String appSecret) throws UnsupportedEncodingException {
        TreeMap<String, String> paramsTreeMap = new TreeMap<String, String>();
        for (NameValuePair nameValuePair : nvps) {
            paramsTreeMap.put(nameValuePair.getName(), nameValuePair.getValue());
        }
        return getSign(paramsTreeMap, appSecret);
    }

    public static String getSign(TreeMap<String, String> paramsTreeMap, String appSecret)
            throws UnsupportedEncodingException {
        String signCalc = "";
        for (Map.Entry<String, String> entry : paramsTreeMap.entrySet()) {
            // map中的key
            String key = entry.getKey();
            String value = "";
            value = String.valueOf(entry.getValue());
            signCalc = String.format("%s%s=%s&", signCalc, key, encodedFix(URLEncoder.encode(value, "UTF-8")), "&");
        }
        if (signCalc.length() > 0) {
            signCalc = signCalc.substring(0, signCalc.length() - 1);
        }
        signCalc = DigestUtils.md5Hex(String.format("%s%s", signCalc, appSecret));
        return signCalc;
    }

    private static boolean verifySign(String content, String secret, String sign) {

        return sign.equals(genSign(content, secret));

    }

    public static boolean verifySign(Map<String, String> paramMap, String secret, String sign) {

        return verifySign(getFormatString(paramMap), secret, sign);

    }

    /**
     * <p>copy from another project，and don't know why</p>
     */
    private static String encodedFix(String str) {
        // required
        str = str.replace("+", "%20");
        str = str.replace("*", "%2A");
        str = str.replace("%7E", "~");

        // optional
        str = str.replace("!", "%21");
        str = str.replace("(", "%28");
        str = str.replace(")", "%29");
        str = str.replace("'", "%27");
        return str;
    }
}
