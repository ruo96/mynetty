package com.wrh.tomcat;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author wuruohong
 * @Classname MyResponse
 * @Description TODO
 * @Date 2020/4/26 17:51
 */
@Slf4j
@Data
public class MyResponse {

    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        // http响应协议
        // HTTP/1.1 200ok
        // Content-Type: text/html
        // <html><body></body></html>

        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html\n")
                .append("\r\n")
                .append("<html><body>")
                .append(content)
                .append("</body></html>");

        outputStream.write(httpResponse.toString().getBytes());
        outputStream.close();
    }
}
