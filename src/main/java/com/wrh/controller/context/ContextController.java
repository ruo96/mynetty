package com.wrh.controller.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wuruohong
 * @Classname ContextController
 * @Description TODO
 * @Date 2021/3/23 15:09
 */
@Slf4j
@RestController
@RequestMapping("/context/")
public class ContextController {

    @RequestMapping("locale")
    public String getLocaleContext(HttpServletRequest req) {
        log.info(">>> locale: {}" , LocaleContextHolder.getLocale());
        log.info(">>> getCountry: {}" , LocaleContextHolder.getLocale().getCountry());
        log.info(">>> getDisplayCountry: {}" , LocaleContextHolder.getLocale().getDisplayCountry());
        log.info(">>> getDisplayLanguage: {}" , LocaleContextHolder.getLocale().getDisplayLanguage());
        log.info(">>> getDisplayName: {}" , LocaleContextHolder.getLocale().getDisplayName());
        log.info(">>> getDisplayScript: {}" , LocaleContextHolder.getLocale().getDisplayScript());
        log.info(">>> getDisplayVariant: {}" , LocaleContextHolder.getLocale().getDisplayVariant());
        return "ok";
    }

    @RequestMapping("attr")
    public String getAttr(HttpServletRequest req) {
        RequestAttributes previousAttributes = RequestContextHolder.getRequestAttributes();
        log.info(">>> attr: {}" , previousAttributes);
        log.info(">>> getSessionId: {}" , previousAttributes.getSessionId());
        return "ok";
    }

    @RequestMapping("req")
    public String getReq(HttpServletRequest req) throws IOException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        String name = (String) request.getParameter("name");
        log.info(">>> name: {}" , name);
        PrintWriter writer = response.getWriter();
        writer.write("real");
        writer.flush();
        return "ok";
//        return "ok";
    }




}
