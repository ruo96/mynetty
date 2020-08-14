package com.wrh.utils;

import com.alibaba.fastjson.JSON;
import com.wrh.utils.test.Dog;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author: cuijiateng
 * @Description:
 * @Date: Created in 2019-01-15 21:50
 * @Modified By:
 */
public class ChineseCharacterUtil {
    /**
     * 获取汉字首字母或全拼大写字母
     *
     * @param chinese 汉字
     * @param isFull  是否全拼 true:表示全拼 false表示：首字母
     * @return 全拼或者首字母大写字符窜
     */
    /*public static String getUpperCase(String chinese, boolean isFull) {
        return convertHanzi2Pinyin(chinese, isFull).toUpperCase();
    }*/
    
    /**
     * 获取汉字首字母或全拼小写字母
     *
     * @param chinese 汉字
     * @param isFull  是否全拼 true:表示全拼 false表示：首字母
     * @return 全拼或者首字母小写字符窜
     */
    /*public static String getLowerCase(String chinese, boolean isFull) {
        return convertHanzi2Pinyin(chinese, isFull).toLowerCase();
    }*/
    
    /**
     * 将汉字转成拼音
     * <p>
     * 取首字母或全拼
     *
     * @param hanzi  汉字字符串
     * @param isFull 是否全拼 true:表示全拼 false表示：首字母
     * @return 拼音
     */
    /*private static String convertHanzi2Pinyin(String hanzi, boolean isFull) {
        *//***
         * ^[\u2E80-\u9FFF]+$ 匹配所有东亚区的语言
         * ^[\u4E00-\u9FFF]+$ 匹配简体和繁体
         * ^[\u4E00-\u9FA5]+$ 匹配简体
         *//*
        String regExp = "^[\u4E00-\u9FFF]+$";
        StringBuffer sb = new StringBuffer();
        if (hanzi == null || "".equals(hanzi.trim())) {
            return "";
        }
        String pinyin = "";
        for (int i = 0; i < hanzi.length(); i++) {
            char unit = hanzi.charAt(i);
            //是汉字，则转拼音
            if (match(String.valueOf(unit), regExp)) {
                pinyin = convertSingleHanzi2Pinyin(unit);
                if (isFull) {
                    sb.append(pinyin);
                } else {
                    sb.append(pinyin.charAt(0));
                }
            } else {
                sb.append(unit);
            }
        }
        return sb.toString();
    }*/
    
    /**
     * 将单个汉字转成拼音
     *
     * @param hanzi 汉字字符
     * @return 拼音
     */
   /* private static String convertSingleHanzi2Pinyin(char hanzi) {
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String[] res;
        StringBuffer sb = new StringBuffer();
        try {
            res = PinyinHelper.toHanyuPinyinStringArray(hanzi, outputFormat);
            sb.append(res[0]);//对于多音字，只用第一个拼音
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sb.toString();
    }*/
    
    /***
     * 匹配
     * <P>
     * 根据字符和正则表达式进行匹配
     *
     * @param str 源字符串
     * @param regex 正则表达式
     *
     * @return true：匹配成功  false：匹配失败
     */
    private static boolean match(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    private static boolean isChineseChar(char c){
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

    private static boolean isChineseString(String str){
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 判定输入的是否是汉字
     *
     * @param c
     *  被校验的字符
     * @return true代表是汉字
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 校验String是否全是中文
     *
     * @param name
     *  被校验的字符串
     * @return true代表全是汉字
     */
    public static boolean isAllChinese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (!isChinese(cTemp[i])) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * 判断是否具有汉字
     * @param name
     * @return
     */
    public static boolean isContainChinese(String name) {
        boolean res = false;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (isChinese(cTemp[i])) {
                res = true;
                return res;
            }
        }
        return false;
    }

    /**
     * 获取第一组完整汉字
     * @param name
     * @return
     */
    public static String filterChinese(String name) {
        StringBuilder sb = new StringBuilder();
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (isChinese(cTemp[i])) {
                System.out.println("是中文  " + i + "   "+ cTemp[i] + "  "+ sb.toString());
                sb.append(cTemp[i]);
            }else if(!isChinese(cTemp[i]) && sb.length() == 0) {
                System.out.println("不是中文  " + i + ""+ cTemp[i] +"  " +sb.toString());
                continue;
            }else if(!isChinese(cTemp[i]) && sb.length() > 0) {
                System.out.println("结束1  " + i );
                return sb.toString();
            }else if(i == (name.length() -1)  && sb.length()>0){
                System.out.println("结束2  " + i );
                return sb.toString();
            }
        }
        if(sb.length() > 0){
            return sb.toString();
        }else {
            return "";
        }
    }

    /**
     * 测试方法
     */
    public static void main(String[] args) {

//        System.out.println(convertHanzi2Pinyin("魑魅魍魉", false).toUpperCase());
        System.out.println(isChineseChar("中国".toCharArray()[0]));
        System.out.println(isChineseString("中国"));
        System.out.println(isAllChinese("中国"));
        System.out.println("中国人民".contains("中国"));
        System.out.println(filterChinese("123中国123人民"));

        List<Dog> dogs = new ArrayList<>();
        Dog d1 = new Dog();
        d1.setColor("黄色");
        d1.setName("大黄狗3");
        d1.setAge(1);
        d1.setOwner("小1");

        Dog d2 = new Dog();
        d2.setColor("黑色");
        d2.setName("大黄狗1");
        d2.setAge(2);
        d2.setOwner("小2");

        Dog d3 = new Dog();
        d3.setColor("白色");
        d3.setName("大黄狗4");
        d3.setAge(3);
        d3.setOwner("小3");

        dogs.add(d1);
        dogs.add(d2);
        dogs.add(d3);
        System.out.println("list: " + JSON.toJSONString(dogs));
        String searchName = "黄2";
        if(ChineseCharacterUtil.isAllChinese(searchName)){
            dogs = dogs.stream().filter(gameBaseInfo ->
                    gameBaseInfo.getName().contains(searchName))
                    .collect(Collectors.toList());
        }else if(ChineseCharacterUtil.isContainChinese(searchName)){
            dogs = dogs.stream().filter(gameBaseInfo ->
                    gameBaseInfo.getName().contains(ChineseCharacterUtil.filterChinese(searchName)))
                    .collect(Collectors.toList());
        }else{
            /*dogs = dogs.stream().filter(gameBaseInfo ->
                    ChineseCharacterUtil.getLowerCase(gameBaseInfo.getGameName(), false).contains(ChineseCharacterUtil.getLowerCase(searchName, false)))
                    .collect(Collectors.toList());*/
        }
        System.out.println("after list: " + JSON.toJSONString(dogs));

        dogs = dogs.stream().sorted(Comparator.comparing(Dog::getName)).collect(Collectors.toList());
        System.out.println("after SORT list: " + JSON.toJSONString(dogs));


    }


}
