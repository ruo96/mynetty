package com.wrh.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by
 */
public class RegexUtils {

    public static String getMatcherValue(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()){
            return matcher.group(1);
        }
        else{
            return "";
        }
    }

}
