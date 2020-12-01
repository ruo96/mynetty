package com.wrh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author wuruohong
 * @Classname CookieController
 * @Description TODO
 * @Date 2020/11/30 10:52
 */
@Slf4j
@RestController
public class CookieController {

    /**
     * Cookie的特点和作用
     * Cookie存储数据在客户端浏览器
     * 浏览器对于单个Cookie的大小限制，以及对同一个域名下的总Cookie数量也有限制（20个）
     *
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/cookie/query")
    public String property(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        Cookie[] cookies = request.getCookies();

        if(cookies!=null){
            for(Cookie cookie:cookies){
                System.out.println(cookie.getName() + "->" + cookie.getValue());
            }
        }


        //1.创建Cookie对象
        Cookie c = new Cookie("msg"+ LocalDateTime.now().getSecond(),"hello");
//        存活时间  秒
        /*int 取值分类
    正数:将Cookie数据写到硬盘文件中，持久化存储，Cookie 存活时间（单位为：秒）
    负数：默认值
    零：删除Cookie信息
   */
        c.setMaxAge(5);
//        设置域名， 这样 tieba.baidu.com 和 news.baidu.com的cookie可以共享
        c.setDomain(".baidu.com");
        //2.发送Cookie
        response.addCookie(c);


        //存中文到Cookie编码
        Cookie cookie = new Cookie("uname", URLEncoder.encode("中文","utf-8"));
        response.addCookie(cookie);


        //获取Cookie解码
        String value = URLDecoder.decode(cookie.getValue(),"utf-8");
        return "response";
    }
}
