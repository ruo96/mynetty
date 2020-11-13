package com.wrh.charUse;

import cn.hutool.core.util.CharsetUtil;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.wrh.utils.ChineseCharacterUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:47 2019/10/12 0012
 * @Modified By:
 */
@Slf4j
public class TestCharUse {

    @Test
    public void test(){
        char a = '我';
        log.info("===> a: {}",a);
    }

    /**
     * 繁体转简体  简体转繁体
     */
    @Test
    public void Test22() {
        String s = "123";
        String f = CharsetUtil.convert(s, "utf-8", "GBK");
        System.out.println(f);

        String name = ZhConverterUtil.convertToTraditional(s);
        System.out.println(name);

        String newName = ZhConverterUtil.convertToSimple(name);
        System.out.println(newName);


    }

    /**
     * 汉字的  全拼 首字母  繁体和简体都支持
     */
    @Test
    public void Test41() {
        String s =  "銀行";
        String pinyin = ChineseCharacterUtil.getLowerCase(s, true);
        String shouzimu = ChineseCharacterUtil.getLowerCase(s, false);
        System.out.println(pinyin);
        System.out.println(shouzimu);
        System.out.println(ChineseCharacterUtil.isContainChinese(s));
        System.out.println(ChineseCharacterUtil.filterChinese(s));

    }
    
    @Test
    public void Test55() {
        String a = "剑侠世界(终止运营)";
//        System.out.println(ChineseCharacterUtil.convertSingleHanzi2PinyinWithMulti(a.charAt(0)));
        List<String> pinyin = ChineseCharacterUtil.getLowerCaseWithMultiTone(a, true);
        System.out.println("result is : "+pinyin);
    }

    @Test
    public void Test68() {
        String a = "AbcD";
        System.out.println(a);
        a = a.toLowerCase();
        System.out.println(a);

    }

    @Test
    public void Test77() {
        List<String> list = new ArrayList<>();
        list.add("WRH1");
        list.add("WRH2");
        list.add("wrh3");
        list.add("wrH4");

        System.out.println(list);
        /*list.stream().forEach(e->{
            e = e.toLowerCase();
        });*/

        list = list.stream().map(e->e.toLowerCase()).collect(Collectors.toList());
        System.out.println(list);


    }
}
