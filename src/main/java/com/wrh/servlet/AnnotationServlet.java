package com.wrh.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wuruohong
 * @Classname AnnotationServlet
 * @Description TODO
 * @Date 2020/10/13 11:04
 */
@WebServlet(urlPatterns = "/annotation")
public class AnnotationServlet extends HttpServlet {
    /**
     * 1. @WebServlet
     *
     * 在自定义的 servlet 上添加 Servlet3+的注解@WebServlet，来声明这个类是一个 Servlet
     *
     * 和 Fitler 的注册方式一样，使用这个注解，需要配合 Spring Boot 的@ServletComponentScan，否则单纯的添加上面的注解并不会生效
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        PrintWriter writer = resp.getWriter();
        writer.write("[AnnotationServlet] welcome " + name);
        writer.flush();
        writer.close();
    }
}
