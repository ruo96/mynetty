package com.wrh.enums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumSet;

/**
 * @author wuruohong
 * @Classname EnumSuccessOrError
 * @Description TODO
 * @Date 2020/12/15 15:51
 */
public enum EnumSuccessOrError {
    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR");
    /**
     * 返回状态码
     */
    private int statusCode;
    /**
     * 返回状态信息
     */
    private String statusMsg;



    EnumSuccessOrError(int statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    private MessageSource messageSource;

    public EnumSuccessOrError setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
        return this;
    }

    //通过静态内部类的方式注入bean，并赋值到枚举中
    @Component
    public static class ReportTypeServiceInjector {

        @Autowired
        private MessageSource messageSource;

        @PostConstruct
        public void postConstruct() {
            for (EnumSuccessOrError rt : EnumSet.allOf(EnumSuccessOrError.class)) {
                rt.setMessageSource(messageSource);
            }
        }
    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @return the statusMsg，根据语言环境返回国际化字符串
     */
    public String getStatusMsg() {
        return messageSource.getMessage(statusMsg,null,statusMsg, LocaleContextHolder.getLocale());
    }
}
