package com.wrh.big.utils;



//import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * http url client 基于apache httpclient4.1 用于http的api接口
 *
 * @author zhangjing3
 */
@Component(value = "httpApiClient")
public class HttpApiClient implements InitializingBean {
    private HttpClient client;
    private int maxTotalConnections;
    private int waitTimeout;
    private int maxRouteConnections;
    private int connectTimeout;
    private int readTimeout;
    private boolean suportHttps = true;
    /**
     * 最大连接数
     */

    public final static int DEFAULT_MAX_TOTAL_CONNECTIONS = 800;

    /**
     * 获取连接的最大等待时间
     */

    public final static int DEFAULT_WAIT_TIMEOUT = 6000;

    /**
     * 每个路由最大连接数
     */

    public final static int DEFAULT_MAX_ROUTE_CONNECTIONS = 400;

    /**
     * 连接超时时间
     */

    public final static int DEFAULT_CONNECT_TIMEOUT = 5000;

    /**
     * 读取超时时间
     */

    public final static int DEFAULT_READ_TIMEOUT = 10000;

    public int getMaxTotalConnections() {
        if (maxTotalConnections == 0) {
            maxTotalConnections = DEFAULT_MAX_TOTAL_CONNECTIONS;
        }
        return maxTotalConnections;
    }

    public void setMaxTotalConnections(int maxTotalConnections) {
        this.maxTotalConnections = maxTotalConnections;
    }

    public int getWaitTimeout() {
        if (waitTimeout == 0) {
            waitTimeout = DEFAULT_WAIT_TIMEOUT;
        }
        return waitTimeout;
    }

    public void setWaitTimeout(int waitTimeout) {
        this.waitTimeout = waitTimeout;
    }

    public int getMaxRouteConnections() {
        if (maxRouteConnections == 0) {
            maxRouteConnections = DEFAULT_MAX_ROUTE_CONNECTIONS;
        }
        return maxRouteConnections;
    }

    public void setMaxRouteConnections(int maxRouteConnections) {
        this.maxRouteConnections = maxRouteConnections;
    }

    public int getConnectTimeout() {
        if (connectTimeout == 0) {
            connectTimeout = DEFAULT_CONNECT_TIMEOUT;
        }
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        if (readTimeout == 0) {
            readTimeout = DEFAULT_READ_TIMEOUT;
        }
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public HttpClient getClient() {
        return client;
    }

    public void setClient(HttpClient client) {
        this.client = client;
    }

    public boolean isSuportHttps() {
        return suportHttps;
    }

    public void setSuportHttps(boolean suportHttps) {
        this.suportHttps = suportHttps;
    }

    public String post(String baseUrl, String url, List<NameValuePair> nvp, Header... headers)
            throws Exception {
        HttpPost httpPost = null;
        InputStream in = null;
        HttpEntity responseEntity = null;
        try {
            httpPost = new HttpPost(String.format("%s%s", baseUrl, url));
            if (nvp != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(nvp, "UTF-8"));
            }
            if (headers != null) {
                httpPost.setHeaders(headers);
            }

            HttpResponse response = client.execute(httpPost);
            responseEntity = response.getEntity();
            if (responseEntity != null) {
                String content = EntityUtils.toString(responseEntity);
                in = response.getEntity().getContent();
                return content;
            }
        } finally {
            if (httpPost != null) {
                httpPost.abort();
            }
            if (responseEntity != null) {
                EntityUtils.consume(responseEntity);
            }
            IOUtils.closeQuietly(in);
        }
        return null;
    }

    public String postData(String baseUrl, String url, String body, Header... headers)
            throws Exception {
        HttpPost httpPost = null;
        InputStream in = null;
        HttpEntity responseEntity = null;
        try {
            httpPost = new HttpPost(String.format("%s%s", baseUrl, url));
            httpPost.setEntity(new StringEntity(body, "UTF-8"));
            if (headers != null) {
                httpPost.setHeaders(headers);
            }

            HttpResponse response = client.execute(httpPost);
            responseEntity = response.getEntity();
            if (responseEntity != null) {
                String content = EntityUtils.toString(responseEntity);
                in = response.getEntity().getContent();
                return content;
            }
        } finally {
            if (httpPost != null) {
                httpPost.abort();
            }
            if (responseEntity != null) {
                EntityUtils.consume(responseEntity);
            }
            IOUtils.closeQuietly(in);
        }
        return null;
    }

    public String postDataWithHeaderMap(String baseUrl, String url, String body, Map<String, String> headerMap)
            throws Exception {
        HttpPost httpPost = null;
        InputStream in = null;
        HttpEntity responseEntity = null;
        try {
            httpPost = new HttpPost(String.format("%s%s", baseUrl, url));
            httpPost.setEntity(new StringEntity(body, "UTF-8"));

            if (null != headerMap){
                Set<Map.Entry<String, String>> entrySet = headerMap.entrySet();
                for (Map.Entry<String, String> entry: entrySet) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }

            HttpResponse response = client.execute(httpPost);
            responseEntity = response.getEntity();
            if (responseEntity != null) {
                String content = EntityUtils.toString(responseEntity);
                in = response.getEntity().getContent();
                return content;
            }
        } finally {
            if (httpPost != null) {
                httpPost.abort();
            }
            if (responseEntity != null) {
                EntityUtils.consume(responseEntity);
            }
            IOUtils.closeQuietly(in);
        }
        return null;
    }
    
    public String postDataUtf8(String baseUrl, String url, String body, Header... headers)
            throws Exception {
        HttpPost httpPost = null;
        InputStream in = null;
        HttpEntity responseEntity = null;
        try {
            httpPost = new HttpPost(String.format("%s%s", baseUrl, url));
            httpPost.setEntity(new StringEntity(body,"UTF-8"));
            if (headers != null) {
                httpPost.setHeaders(headers);
            }

            HttpResponse response = client.execute(httpPost);
            responseEntity = response.getEntity();
            if (responseEntity != null) {
                String content = EntityUtils.toString(responseEntity,"UTF-8");
                in = response.getEntity().getContent();
                return content;
            }
        } finally {
            if (httpPost != null) {
                httpPost.abort();
            }
            if (responseEntity != null) {
                EntityUtils.consume(responseEntity);
            }
            IOUtils.closeQuietly(in);
        }
        return null;
    }


    public byte[] postDataGetBytes(String baseUrl, String url, String body, Header... headers)
            throws Exception {
        HttpPost httpPost = null;
        InputStream in = null;
        HttpEntity responseEntity = null;
        try {
            httpPost = new HttpPost(String.format("%s%s", baseUrl, url));
            httpPost.setEntity(new StringEntity(body, "UTF-8"));
            if (headers != null) {
                httpPost.setHeaders(headers);
            }

            HttpResponse response = client.execute(httpPost);
            responseEntity = response.getEntity();
            if (responseEntity != null) {
                byte[] content = EntityUtils.toByteArray(responseEntity);
                in = response.getEntity().getContent();
                return content;
            }
        } finally {
            if (httpPost != null) {
                httpPost.abort();
            }
            if (responseEntity != null) {
                EntityUtils.consume(responseEntity);
            }
            IOUtils.closeQuietly(in);
        }
        return null;
    }


    public String get(String baseUrl, String url, List<NameValuePair> nvp, Header... headers)
            throws Exception {
        HttpGet httpGet = null;
        InputStream in = null;
        HttpEntity responseEntity = null;
        try {
            if (nvp != null && nvp.size() > 0) {
                String queryString = "";
                queryString = EntityUtils.toString(new UrlEncodedFormEntity(
                        nvp, "UTF-8"));
                httpGet = new HttpGet(String.format("%s%s?%s", baseUrl, url,
                        queryString));
            } else {
                httpGet = new HttpGet(String.format("%s%s", baseUrl, url));
            }
            if (headers != null) {
                httpGet.setHeaders(headers);
            }

            HttpResponse response = client.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            responseEntity = response.getEntity();
            String content = "";
            if (responseEntity != null) {
                content = EntityUtils.toString(responseEntity);
                in = response.getEntity().getContent();
            }
            if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("请求目标系统发生异常，请求url:" + baseUrl + url
                        + ",响应码：" + statusCode + ",内容:" + content);
			}
            return content;
        } finally {
            if (httpGet != null) {
                httpGet.abort();
            }
            if (responseEntity != null) {
                EntityUtils.consume(responseEntity);
            }
            IOUtils.closeQuietly(in);
        }
    }

    public String get(String baseUrl, String url, List<NameValuePair> nvp, List<Header> headers, List<Cookie> cookies)
            throws Exception {
        HttpGet httpGet = null;
        InputStream in = null;
        HttpEntity responseEntity = null;
        try {
            if (nvp != null && nvp.size() > 0) {
                String queryString = "";
                queryString = EntityUtils.toString(new UrlEncodedFormEntity(
                        nvp, "UTF-8"));
                System.out.println(baseUrl+url+"?"+queryString);
                httpGet = new HttpGet(String.format("%s%s?%s", baseUrl, url,
                        queryString));
                //System.out.println(queryString);
            } else {
                httpGet = new HttpGet(String.format("%s%s", baseUrl, url));
            }
            if (headers != null) {
                Header[] headerArrays = headers.toArray(new Header[headers.size()]);
                httpGet.setHeaders(headerArrays);
            }
            HttpResponse response;
            if (cookies != null) {
                BasicCookieStore cookieStore = new BasicCookieStore();
                Cookie[] cookieArrays = cookies.toArray(new Cookie[cookies.size()]);
                cookieStore.addCookies(cookieArrays);
                HttpContext localContext = new BasicHttpContext();
                localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
                response = client.execute(httpGet, localContext);
            } else {
                response = client.execute(httpGet);
            }
            int statusCode = response.getStatusLine().getStatusCode();
            responseEntity = response.getEntity();
            String content = "";
            if (responseEntity != null) {
                content = EntityUtils.toString(responseEntity);
                in = response.getEntity().getContent();
            }
            if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("请求目标系统发生异常，请求url:" + baseUrl + url
                        + ",响应码：" + statusCode + ",内容:" + content);
			}
            return content;
        } finally {
            if (httpGet != null) {
                httpGet.abort();
            }
            if (responseEntity != null) {
                EntityUtils.consume(responseEntity);
            }
            IOUtils.closeQuietly(in);
        }
    }


    public byte[] download(String downloadUrl) throws Exception {
        return download(downloadUrl, "", null);
    }

    public byte[] download(String baseUrl, String url, List<NameValuePair> nvp,
                           Header... headers) throws Exception {
        HttpGet httpGet = null;
        InputStream in = null;
        HttpEntity responseEntity = null;
        try {
            if (nvp != null && nvp.size() > 0) {
                String queryString = "";
                queryString = EntityUtils.toString(new UrlEncodedFormEntity(
                        nvp, "UTF-8"));
                httpGet = new HttpGet(String.format("%s%s?%s", baseUrl, url,
                        queryString));
            } else {
                httpGet = new HttpGet(String.format("%s%s", baseUrl, url));
            }
            if (headers != null) {
                httpGet.setHeaders(headers);
            }

            HttpResponse response = client.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            responseEntity = response.getEntity();
            byte[] data = null;
            if (responseEntity != null) {
                data = EntityUtils.toByteArray(responseEntity);
                in = response.getEntity().getContent();
            }
            if (statusCode != HttpStatus.SC_OK) {
				throw new Exception("下载目标URL发生异常，请求url:" + baseUrl + url
                        + ",响应码：" + statusCode);
			}
            return data;
        } finally {
            if (httpGet != null) {
                httpGet.abort();
            }
            if (responseEntity != null) {
                EntityUtils.consume(responseEntity);
            }
            IOUtils.closeQuietly(in);
        }
    }


    public void destroy() {
        client.getConnectionManager().shutdown();
    }

    @SuppressWarnings("deprecation")
    @Override
	public void afterPropertiesSet() throws Exception {
        HttpParams httpParams = new BasicHttpParams();
        // 设置连接超时时间
        HttpConnectionParams.setConnectionTimeout(httpParams,
                getConnectTimeout());
        // 设置读取超时时间
        HttpConnectionParams.setSoTimeout(httpParams, getReadTimeout());

        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", 80, PlainSocketFactory
                .getSocketFactory()));
        if (suportHttps) {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null,
                    new TrustManager[]{new TrustAnyX509TrustManager()},
                    new SecureRandom());
            SSLSocketFactory sf = new SSLSocketFactory(sslContext);
            sf.setHostnameVerifier(new X509HostnameVerifier() {

                @Override
				public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
				public void verify(String arg0, SSLSocket arg1)
                        throws IOException {

                }

                @Override
				public void verify(String arg0, X509Certificate arg1)
                        throws SSLException {

                }

                @Override
				public void verify(String arg0, String[] arg1, String[] arg2)
                        throws SSLException {
                }

            });
            Scheme sch = new Scheme("https", 443, sf);
            registry.register(sch);
        }
        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(
                registry);

        cm.setMaxTotal(getMaxTotalConnections());
        cm.setDefaultMaxPerRoute(getMaxRouteConnections());
        client = new DefaultHttpClient(cm, httpParams);
    }
}
