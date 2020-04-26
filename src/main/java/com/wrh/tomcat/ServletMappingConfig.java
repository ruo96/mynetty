package com.wrh.tomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuruohong
 * @Classname ServletMappingConfig
 * @Description TODO
 * @Date 2020/4/26 18:05
 */
public class ServletMappingConfig {

    public static List<ServletMapping> servletMappingList = new ArrayList<>();

    static {
        servletMappingList.add(new ServletMapping("findGirl","/girl","com.wrh.tomcat.FindGirlServlet"));
        servletMappingList.add(new ServletMapping("helloWorld","/world","com.wrh.tomcat.HelloWorldServlet"));
        servletMappingList.add(new ServletMapping("favicon","/favicon.ico","com.wrh.tomcat.HelloWorldServlet"));
    }
}
