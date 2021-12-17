package com.wrh.controller.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author wuruohong
 * @Classname HttpController
 * @Description 与调用Http接口不一样的部分主要在设置sslSocketFactory和hostnameVerifier部分
 * @Date 2021/12/17 15:19
 */
@Slf4j
@RestController
@RequestMapping("/https/")
public class HttpsController {

    /*
     * 请求有权威证书的地址
     */
    @Test
    public void test() throws IOException {
        String requestPath = "https://www.baidu.com/";
        OkHttpClient client = new OkHttpClient.Builder()
                //.connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
                .build();
        Request request = new Request.Builder()
                .url(requestPath).get().build();
        Response response = client.newCall(request).execute();
        System.out.println("get返回状态：" + response.code());
        System.out.println("get返回结果：" + response.body().string());
        response.close();
    }

    /**
     * 请求自定义证书的地址,不需要客户端证书
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        String requestPath = "https://10.40.103.48:9010/zsywservice";
        //获取信任证书库
        KeyStore trustStore = getkeyStore("jks", "d:/temp/cacerts", "123456");
        //KeyStore trustStore = null; //trustStore为null也可以
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(getSSLSocketFactory(null, null, trustStore), new DefaultTrustManager())
                .hostnameVerifier((s, sslSession) -> true).build();
        Request request = new Request.Builder()
                .url(requestPath).get().build();
        Response response = client.newCall(request).execute();
        System.out.println("get返回状态：" + response.code());
        System.out.println("get返回结果：" + response.body().string());
        response.close();
    }

    /**
     * 请求自定义证书的地址,需要客户端证书
     *
     * @throws IOException
     */
    @Test
    public void test3() throws Exception {
        String requestPath = "https://10.40.103.48:9016/zsywservice";
        //获取客户端证书
        KeyStore keyStore = getkeyStore("pkcs12", "d:/client.p12", "123456");

        //获取信任证书库
        KeyStore trustStore = getkeyStore("jks", "d:/temp/cacerts", "123456");
        //KeyStore trustStore = null; //trustStore为null也可以
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(getSSLSocketFactory(keyStore, "123456", trustStore), new DefaultTrustManager())
                .hostnameVerifier((s, sslSession) -> true).build();
        Request request = new Request.Builder()
                .url(requestPath).get().build();
        Response response = client.newCall(request).execute();
        System.out.println("get返回状态：" + response.code());
        System.out.println("get返回结果：" + response.body().string());
        response.close();
    }

    /**
     * 获取证书
     *
     * @return
     */
    private KeyStore getkeyStore(String type, String filePath, String password) {
        KeyStore keySotre = null;
        FileInputStream in = null;
        try {
            keySotre = KeyStore.getInstance(type);
            in = new FileInputStream(new File(filePath));
            keySotre.load(in, password.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //FileUtil.close(in);
        }
        return keySotre;
    }

    private SSLSocketFactory getSSLSocketFactory(KeyStore keyStore, String keyStorePassword, KeyStore trustStore) throws Exception {
        KeyManager[] keyManagers = null;
        TrustManager[] trustManagers = null;
        if (keyStore != null) {
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());
            keyManagers = keyManagerFactory.getKeyManagers();
        }
        if (trustStore != null) {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(trustStore);
            trustManagers = trustManagerFactory.getTrustManagers();
        } else {
            trustManagers = new TrustManager[]{new DefaultTrustManager()};
        }

        //设置服务端支持的协议
        SSLContext context = SSLContext.getInstance("TLSv1.2");
        context.init(keyManagers, trustManagers, null);
        SSLSocketFactory sslFactory = context.getSocketFactory();

        return sslFactory;
    }

    private final class DefaultTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }
}
