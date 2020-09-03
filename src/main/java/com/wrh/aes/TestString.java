package com.wrh.aes;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 9:26 2019/7/26 0026
 * @Modified By:
 */
@Slf4j
public class TestString {

    //查询溯源信息
    public static final String TRACING_TREE ="/zcs_gateway/apps/c/api/tracing/tree";

    /**微信添加扫码记录*/
    public static final String WX_SCAN_RECORD ="/zcs_gateway/apps/c/api/tracing";
    /**
     * 增加扫码次数
     */
    public static final String ADD_QRCODE_RECODE ="/zcs_gateway/apps/c/api/add/record";

    public static void main(String[] args) {
        String url = "/zcs_gateway/apps/c/api/scanned/code/info";
        System.out.println(StringUtils.startsWithAny(url,
                new String[]{TRACING_TREE,
                        WX_SCAN_RECORD,
                        ADD_QRCODE_RECODE}));

        log.info("hello");
    }

    @Test
    public void Test36() {
        String a = String.format("abc_%s",1);
        System.out.println(a);

    }

    @Test
    public void Test44() {
        String a = "abc/def/xyz/123";
        String b = StringUtils.substringAfterLast(a,"/");
        System.out.println(b);

    }

    @Test
    public void Test52() {
        String str = "  abc  efg  ";
        System.out.println(str);
        System.out.println(str.length());
        str = str.trim();
        System.out.println(str);
        System.out.println(str.length());
    }

    @Test
    public void Test62() {
        String message = "   ";
        Map<String, Object> messageMap = JSONObject.parseObject(message, Map.class);

    }

    /**
     * 查找字符串每个字符的个数
     */
    @Test
    public void Test72() {
        String str = "abcabcde";
        printDistinctCharWithCount(str);

    }
    
    @Test
    public void Test83() {

        
    }

    /**
     * 更加优雅的方法
     * @param str
     */
    private void printDistinctCharWithCountV2(String str) {
        Map<Character, Integer> charsWithCountMap = new LinkedHashMap<>();
        for(char c : str.toCharArray()){
            //如果是第一次出现，那么就是1， 如果不是，那就根据bifunction和第二个参数来计算
            charsWithCountMap.merge(c, 1,Integer::sum);
        }
        System.out.println(charsWithCountMap);
    }

    private void printDistinctCharWithCount(String str) {
        Map<Character, Integer> charsWithCountMap = new LinkedHashMap<>();
        for(char c : str.toCharArray()){
            Integer oldValue = charsWithCountMap.get(c);

            int newValue = (oldValue == null) ? 1 : Integer.sum(oldValue, 1);
            charsWithCountMap.put(c, newValue);

        }
        System.out.println(charsWithCountMap);
    }

    @Test
    public void Test114() {
        String a = "日照香炉生紫烟";
        StringBuilder sb = new StringBuilder(a);
        String ar = sb.reverse().toString();

    }

    @Test
    public void Test122() {
        int a ;
        int b ;
        int c = 10;

        if ((a = c) > 10) {
            System.out.println(c);
        }else {
            System.out.println("not large : " + c);
        }

    }
}
