package com.wrh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;

/**
 * @author wuruohong
 * @Classname CookieController
 * @Description TODO
 * @Date 2020/11/30 10:52
 */
@Slf4j
@RestController
public class SessionController {

    @RequestMapping("/session/no")
    public String noSession(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {


        return "no session";
    }


    @RequestMapping("/session/query")
    public String query(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        HttpSession session = request.getSession();
        session.setAttribute("msg","hello session");

        //期望客户端关闭后，session也能相同
//        Cookie cookie = new Cookie("JESSIONID",session.getId());
//        cookie.setMaxAge(60*60);
//        response.addCookie(cookie);

        return "set session";
    }

    @RequestMapping("/session/get")
    public String get(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        /** 判断是同一个来源，sessionid相同*/
        /**
         * Session的实现是依赖于Cookie的，当服务器创建一个Session时，会在内部记录一个SessionId，而服务器响应数据给客户端时，
         * 会在响应头中设置Cookie: JseesionId:sessionid值的方式，将创建的SessionId 发放到客户端。
         * 而客户端再次请求服务器时，会将客户端的Cookie:JessionId 发送到服务端，服务端根据这个SessionId，就能获取到对应ID的Session对象，拿到会话中的数据。
         *
         * 当客户端关闭后，服务器不关闭，两次获取的Session是否为同一个？
         *
         * 默认情况下，不是。因为一次新的会话，从request中获取的Session对象的SessionId是不一样的。         *
         * 如果需要相同，可以通过创建Cookie，键为 JESSIONID，设置最大存活时间，让Cookie持久化的方法来实现。
         * 因为Session是基于Cookie来是实现的，只要Cookie每次携带的JessionId相同，就是同一个Session。
         */
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("msg");
        log.info(">>> session get msg : {}", msg);
        return "get session";
    }
}
