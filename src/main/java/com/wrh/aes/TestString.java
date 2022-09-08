package com.wrh.aes;

import cn.hutool.Hutool;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrh.elasticsearch.Student;
import com.wrh.reflection.S;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

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

    @Test
    public void Test136() {
        String ds = "2021-06-01";
        System.out.println("ds.substring(0,5) = " + ds.substring(0, 5));
        System.out.println("ds.substring(0,7) = " + ds.substring(0, 7));
        System.out.println("ds.substring(5,7) = " + ds.substring(5, 7));
        System.out.println("Integer.valueOf(ds.substring(5, 7)) = " + Integer.valueOf(ds.substring(5, 7)));
        System.out.println("ds.substring(0, 5) + \"Q\" + (Integer.valueOf(ds.substring(5, 7)) / 3 + 1) = " + ds.substring(0, 5) + "Q" + (Integer.valueOf(ds.substring(5, 7)) / 3 + 1));
        System.out.println("ds.substring(0, 5) + \"Q\" + ((Integer.valueOf(ds.substring(5, 7)) - 1) / 3 + 1) = " + ds.substring(0, 5) + "Q" + ((Integer.valueOf(ds.substring(5, 7)) - 1) / 3 + 1));

    }

    @Test
    public void Test148() {
        String a = "+199";
        Integer b = Integer.valueOf(a);
        System.out.println("b = " + b);

    }

    @Test
    public void Test156() {
        String a = null;
        String key = String.format("abc_%s", a);
        System.out.println("key = " + key);

    }

    @Test
    public void Test164() {
        Student s = new Student();
        s.setName("w1");
        System.out.println(s);
        System.out.println(JSON.toJSONString(s));

        System.out.println(JSON.toJSONString(s, SerializerFeature.WriteMapNullValue));

        /*System.out.println(JSON.toJSONString(s, (ValueFilter)(object, name, value){
            if (value == null) {
                return "";
            }
            return value;
        }));*/

        System.out.println(JSON.toJSONString(s, new PropertyFilter() {
            @Override
            public boolean apply(Object o, String name, Object value) {
                return name.equals("id") || value != null;
            }
        }, SerializerFeature.WriteMapNullValue));

    }

    @Test
    public void Test195() {
        String a = "大     陆";
        String b = a.trim();
        String c = StringUtils.trimToNull(a);
        String d = StringUtils.trimToEmpty(a);
        String e = a.replace(" ","");
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("d = " + d);
        System.out.println("e = " + e);

    }


    private static final int MAX_LOOP = 10000;

    @Test
    public void Test210() {
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.toString());
        for (int i = 0; i < 1000; i++) {
            sb.append(new Random().nextInt()).append(" ");
        }
        split(sb.toString());
        stringTokenizer(sb.toString());
        
    }

    /**
     * @author: 栈长
     * @from: 公众号Java技术栈
     */
    private static void split(String str) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_LOOP; i++) {
            String[] arr = str.split(" ");
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < arr.length; j++) {
                sb.append(arr[j]);
            }
        }
        System.out.printf("split 耗时 %s ms\n", System.currentTimeMillis() - start);
    }

    /**
     * @author: 栈长
     * @from: 公众号Java技术栈
     */
    private static void stringTokenizer(String str) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_LOOP; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, " ");
            StringBuilder sb = new StringBuilder();
            while (stringTokenizer.hasMoreTokens()) {
                sb.append(stringTokenizer.nextToken());
            }
        }
        System.out.printf("StringTokenizer 耗时 %s ms", System.currentTimeMillis() - start);
    }

    @Test
    public void Test259() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(new stu("s1", "n1", 18));
        System.out.println("s = " + s);
    }

    class stu {
        private String name;
        @JsonIgnore
        private String nickname;
        private Integer age;

        public stu(String name, String nickname, Integer age) {
            this.name = name;
            this.nickname = nickname;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
