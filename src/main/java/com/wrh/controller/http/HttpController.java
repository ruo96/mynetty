package com.wrh.controller.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author wuruohong
 * @Classname HttpController
 * @Description TODO
 * @Date 2021/12/17 15:19
 */
@Slf4j
@RestController
@RequestMapping("/http/")
public class HttpController {

    @RequestMapping("get")
    public String reqhttp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestPath = "https://www.baidu.com";
        OkHttpClient client = new OkHttpClient();
        Request request1 = new Request.Builder().url(requestPath).get().build();
        Response response1 = client.newCall(request1).execute();
        System.out.println("get path:" + requestPath +" 返回状态：" + response1.code());
        System.out.println("get path:" + requestPath +" 返回：" + response1.body().string());
        System.out.println("response1.message() = " + response1.message());

        return "ok";
    }

    @RequestMapping("post")
    public String post(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestPath = "http://www.baidu.com";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("name","wrh").build();

        Request request1 = new Request.Builder().url(requestPath).post(body).build();
        Response response1 = client.newCall(request1).execute();
        System.out.println("get path:" + requestPath +" 返回状态：" + response1.code());
        System.out.println("get path:" + requestPath +" 返回：" + response1.body().string());

        return "ok";
    }

    @RequestMapping("post/json")
    public String postJSON(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestPath = "http://www.baidu.com";
        OkHttpClient client = new OkHttpClient();
        String param = "{\"userId\": \"1001\",\"userName\":\"杜甫\"}";
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), param);

        Request request1 = new Request.Builder().url(requestPath).post(body).build();
        Response response1 = client.newCall(request1).execute();
        System.out.println("get path:" + requestPath +" 返回状态：" + response1.code());
        System.out.println("get path:" + requestPath +" 返回：" + response1.body().string());

        return "ok";
    }

    @RequestMapping("upload/file")
    public String uploadFile() throws IOException {
        String requestPath = "http://www.baidu.com";
        OkHttpClient client = new OkHttpClient();
        File file = new File("d:/a.jpg");
        RequestBody body = RequestBody.create(MediaType.parse("file/*"), file);
        Request request = new Request.Builder()
                .url(requestPath)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println("get path:" + requestPath +" 返回状态：" + response.code());
        System.out.println("get path:" + requestPath +" 返回：" + response.body().string());

        return "ok";
    }

    @RequestMapping("upload/fileandparam")
    public String uploadFileAndParam() throws IOException {
        String requestPath = "http://www.baidu.com";
        OkHttpClient client = new OkHttpClient();
        File file = new File("d:/a.jpg");
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/png"), file))
                .addFormDataPart("param1", "参数1")
                .addFormDataPart("param2", "参数2")
                .build();
        Request request = new Request.Builder()
                .url(requestPath)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println("get path:" + requestPath +" 返回状态：" + response.code());
        System.out.println("get path:" + requestPath +" 返回：" + response.body().string());

        return "ok";
    }
}
