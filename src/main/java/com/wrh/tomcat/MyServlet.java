package com.wrh.tomcat;

/**
 * @author wuruohong
 * @Classname MyServlet
 * @Description Tomcat是满足Servlet规范的容器，那么自然Tomcat需要提供API。
 * 这里看到了Servlet常见的doGet/doPost/service方法。
 * @Date 2020/4/26 17:56
 */
public abstract class MyServlet {

    public abstract void doGet(MyRequest myRequest, MyResponse myResponse);

    public abstract void doPost(MyRequest myRequest, MyResponse myResponse);

    public void service(MyRequest myRequest, MyResponse myResponse){
        if(myRequest.getMethod().equalsIgnoreCase("POST")){
            doPost(myRequest,myResponse);
        }else if(myRequest.getMethod().equalsIgnoreCase("GET")) {
            doGet(myRequest, myResponse);
        }
    }

}
