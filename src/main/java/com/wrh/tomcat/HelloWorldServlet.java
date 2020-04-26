package com.wrh.tomcat;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author wuruohong
 * @Classname FindGirlServlet
 * @Description 具体的Servlet实现，只是为了后续的测试
 * @Date 2020/4/26 18:01
 */
@Slf4j
public class HelloWorldServlet extends MyServlet {
    @Override
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("get world...");
        } catch (IOException e) {
            log.error(" exception: {}", e);
        }
    }

    @Override
    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        try {
            myResponse.write("post world...");
        } catch (IOException e) {
            log.error(" exception: {}", e);
        }
    }
}
