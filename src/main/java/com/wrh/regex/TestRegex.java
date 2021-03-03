package com.wrh.regex;

import com.wrh.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wuruohong
 * @Classname TestRegex
 * @Description TODO
 * @Date 2020/6/15 17:34
 */
@Slf4j
public class TestRegex {

    private static final String TIME_REGEX = "(?:^|&)now=(.*?)&";

    private static final String SEPERATOR_SINGLE = "`SEPERATOR`";

    private static final String SEPERATOR_SINGLE_2 = "@`SEPERATOR`@";

    public static long parseNow(String data) {
        return Long.parseLong(RegexUtils.getMatcherValue(TIME_REGEX, data));
    }

    @Test
    public void Test() {
        String data = "123123412&now=12344321&kdsljf";
        long timeStamp = parseNow(data);
        System.out.println(timeStamp);


        /*String data1 = "1231`SEPERATOR`23412&now=12344321&kdsljf";
        String[] d = data1.split(SEPERATOR_SINGLE);
        for (String s: d){
            System.out.println(s);
        }*/

        String data1 = "1231@`SEPERATOR`@23412&now=12344321&kdsljf";
        String[] d = data1.split(SEPERATOR_SINGLE_2);
        for (String s: d){
            System.out.println(s);
        }
    }

    @Test
    public void Test47() {
        String regex = "\\d*";

        String str = "127.0.0.1";

        /*Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());*/

        boolean isMatch = Pattern.matches(regex, str);
        System.out.println(isMatch);

    }

    @Test
    public void Test65() {
        String content = "I am noob " +
                "from runoob.com.";

        String pattern = ".*runoob.*";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);

    }

    @Test
    public void Test77() {
        String str = "ruo123456";

        String regex = "\\^ruo";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher("ruo123456");
        boolean isMatch = Pattern.matches(regex, str);
        System.out.println(m.matches());
        System.out.println(isMatch);

    }

    @Test
    public void Test90() {
//        String regex = "(/d{3}/s/d{3}-/d{4})";
        String regex = "\\(\\d{3}\\)\\s\\d{3}-\\d{4}";
        String str = ":(212) 555-1212";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if(m.find()){
            System.out.println("find");
            System.out.println(m.group());
        }else {
            System.out.println("not found");
        }

    }

    @Test
    public void Test106() {
//        String regex = "(/d{3}/s/d{3}-/d{4})";
        String regex = "\\(\\d{3}\\)\\s\\d{3}-\\d{4}";
        String str = ":(212) 555-1212";
        boolean m = Pattern.matches(regex, str);
        if(m){
            System.out.println("find");
        }else {
            System.out.println("not found");
        }

    }

    /**
     *
     */
    @Test
    public void Test120() {
        String regex = "172.1[6-9].2";
        String str = "172.16.12";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        /*if(m.matches()){
            System.out.println("match");
        }else {
            System.out.println("not match");
        }*/

        if (m.find()) {
            System.out.println("find");
            System.out.println(m.group());
            System.out.println(m.replaceAll("replace"));
        } else {
            System.out.println("not find");
        }

    }

    @Test
    public void Test145() {

        /**
         *  英文符号.只匹配一个字符，多个是不匹配的
         */
        String regex = "t.n";
        String str = "ton";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);

        if (m.find()) {
            System.out.println("find");
            System.out.println(m.group());
            System.out.println(m.replaceAll("replace"));
        } else {
            System.out.println("not find");
        }
    }

    @Test
    public void Test165() {

        /**
         *  []只匹配内部的一个
         */
        String regex = "t[aoef]n";
        String str = "tin";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);

        if (m.find()) {
            System.out.println("find");
        } else {
            System.out.println("not find");
        }
    }

    @Test
    public void Test183() {
        /**
         *  | 匹配任意一个
         */
        String regex = "t(a|b|c)n";
        String str = "tan";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);

        if (m.find()) {
            System.out.println("find");
        } else {
            System.out.println("not find");
        }

    }

    @Test
    public void Test201() {
        String str = "1234a";
        boolean match = str.matches("\\d+\\w");
        System.out.println(match);

    }

    @Test
    public void Test209() {
        String regex = "\\d+@qq.com$";
        Pattern pattern = Pattern.compile(regex);
        String  str = "1@qq.com";
        if (pattern.matcher(str).find()) {
            System.out.println("match");
        } else {
            System.out.println("not match");
        }

        if (pattern.matcher(str).matches()) { // 匹配整个字符串
            System.out.println("match");
        } else {
            System.out.println("not match");
        }

    }


}
