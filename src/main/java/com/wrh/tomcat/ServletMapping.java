package com.wrh.tomcat;

import lombok.Data;

/**
 * @author wuruohong
 * @Classname ServletMapping
 * @Description TODO
 * @Date 2020/4/26 18:03
 */
@Data
public class ServletMapping {

    private String servletName;

    private String url;

    private String clazz;

    public ServletMapping(String servletName, String url, String clazz) {
        this.servletName = servletName;
        this.url = url;
        this.clazz = clazz;
    }


}
