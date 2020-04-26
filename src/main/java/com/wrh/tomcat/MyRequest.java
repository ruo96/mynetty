package com.wrh.tomcat;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wuruohong
 * @Classname MyRequest
 * @Description TODO
 * @Date 2020/4/26 17:46
 */
@Slf4j
@Data
public class MyRequest {

    private String url;

    private String method;

    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];
        int length = 0;
        if((length = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes,0,length);
        }

        String httpHead = httpRequest.split("\n")[0];
        url = httpHead.split("\\s")[1];
        method = httpHead.split("\\s")[0];
        log.info(">>> 解析请求{}",this);
    }

}
