package com.wrh.nettyhttpserver;

import com.wrh.nettyhttpserver.config.NettyHttpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;

/**
 * @author wuruohong
 * @Classname MagicWebServer
 * @Description TODO
 * @Date 2022/2/28 20:15
 */
public class MagicWebServer {

    private static Logger logger = LoggerFactory.getLogger(MagicWebServer.class);

    public static void main(String[] args) {
        //ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(NettyHttpConfig.class);
        Integer port = 6001;
        DispatcherServlet servlet = getDispatcherServlet(ctx);
        NettyHttpServer server = new NettyHttpServer(port,servlet);
        server.start();

    }

    public static DispatcherServlet getDispatcherServlet(ApplicationContext ctx){

        /*XmlWebApplicationContext mvcContext = new XmlWebApplicationContext();
        mvcContext.setConfigLocation("classpath:beans.xml");
        mvcContext.setParent(ctx);*/

        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.setParent(ctx);
        mvcContext.scan("com.wrh.nettyhttpserver");
        mvcContext.refresh();
        //mvcContext.refresh();
        // ;

        MockServletConfig servletConfig = new MockServletConfig(mvcContext.getServletContext(), "dispatcherServlet");
        DispatcherServlet dispatcherServlet = new DispatcherServlet(mvcContext);
        try {
            dispatcherServlet.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return dispatcherServlet;
    }
}
