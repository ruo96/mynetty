package com.wrh.controller;

import com.wrh.annotate.annotation.PassportTotal;
import com.wrh.enums.EnumSuccessOrError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * @author wuruohong
 * @Classname HelloController
 * @Description TODO
 * @Date 2020/2/27 11:32
 */
@Slf4j
@RestController
@PassportTotal
public class HelloRestController {

    @Autowired
    private MessageSource messageSource;

    /**
     * 国际化返回数据
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/ha")
    public String hi(HttpServletResponse response) throws IOException {
        log.info(">>> ha  time: {}", LocalDateTime.now());
        LocaleContextHolder.setLocale(Locale.US);
        return messageSource.getMessage("welcome",null, LocaleContextHolder.getLocale());
    }

    /**
     * 带占位符的国际化
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/ha1")
    public String ha1(HttpServletResponse response) throws IOException {
        log.info(">>> ha  time: {}", LocalDateTime.now());
        LocaleContextHolder.setLocale(Locale.CHINA);
        return messageSource.getMessage("hello",new Object[]{"welcome","123456"}, LocaleContextHolder.getLocale());
    }

    /**
     * 枚举的国际化  https://wangsong.blog.csdn.net/article/details/104667731?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1.control
     * 还可以通过get请求参数来处理
     * 否则就要通过在报文头中写入   Accept-Language: zh-CN   zh-US  zh-TW
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/ha2")
    public String ha2(HttpServletResponse response) throws IOException {
        log.info(">>> ha2  time: {}", LocalDateTime.now());
        LocaleContextHolder.setLocale(Locale.CHINA);
        return EnumSuccessOrError.SUCCESS.getStatusMsg();
    }

    /**
     * 语言	简称
     * 简体中文(中国)	zh_CN
     * 繁体中文(中国台湾)	zh_TW
     * 繁体中文(中国香港)	zh_HK
     * 英语(中国香港)	en_HK
     * 英语(美国)	en_US
     * 英语(英国)	en_GB
     * 英语(全球)	en_WW
     * 英语(加拿大)	en_CA
     * 英语(澳大利亚)	en_AU
     * 英语(爱尔兰)	en_IE
     * 英语(芬兰)	en_FI
     * 芬兰语(芬兰)	fi_FI
     * 英语(丹麦)	en_DK
     * 丹麦语(丹麦)	da_DK
     * 英语(以色列)	en_IL
     * 希伯来语(以色列)	he_IL
     * 英语(南非)	en_ZA
     * 英语(印度)	en_IN
     * 英语(挪威)	en_NO
     * 英语(新加坡)	en_SG
     * 英语(新西兰)	en_NZ
     * 英语(印度尼西亚)	en_ID
     * 英语(菲律宾)	en_PH
     * 英语(泰国)	en_TH
     * 英语(马来西亚)	en_MY
     * 英语(阿拉伯)	en_XA
     * 韩文(韩国)	ko_KR
     * 日语(日本)	ja_JP
     * 荷兰语(荷兰)	nl_NL
     * 荷兰语(比利时)	nl_BE
     * 葡萄牙语(葡萄牙)	pt_PT
     * 葡萄牙语(巴西)	pt_BR
     * 法语(法国)	fr_FR
     * 法语(卢森堡)	fr_LU
     * 法语(瑞士)	fr_CH
     * 法语(比利时)	fr_BE
     * 法语(加拿大)	fr_CA
     * 西班牙语(拉丁美洲)	es_LA
     * 西班牙语(西班牙)	es_ES
     * 西班牙语(阿根廷)	es_AR
     * 西班牙语(美国)	es_US
     * 西班牙语(墨西哥)	es_MX
     * 西班牙语(哥伦比亚)	es_CO
     * 西班牙语(波多黎各)	es_PR
     * 德语(德国)	de_DE
     * 德语(奥地利)	de_AT
     * 德语(瑞士)	de_CH
     * 俄语(俄罗斯)	ru_RU
     * 意大利语(意大利)	it_IT
     * 希腊语(希腊)	el_GR
     * 挪威语(挪威)	no_NO
     * 匈牙利语(匈牙利)	hu_HU
     * 土耳其语(土耳其)	tr_TR
     * 捷克语(捷克共和国)	cs_CZ
     * 斯洛文尼亚语	sl_SL
     * 波兰语(波兰)	pl_PL
     * 瑞典语(瑞典)	sv_SE
     * 西班牙语(智利)	es_CL
     */


}
