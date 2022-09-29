package com.wrh.https;

import com.wrh.reflection.S;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : wuruohong
 * @description :SpringBoot两种方式配置 SSL证书，实现HTTPS安全访问
 * 使用JDK自带的工具生成证书
 * 使用FreeSSL提供的证书
 * 使用JDK自带的工具生成证书
 * 确保安装了JDK并正确配置了环境变量；进入你的JAVA_HOME目录中的bin目录；
 *
 * 在这个目录下执行 *
 * //  keytool -genkey -alias (别名) -dname "CN=(姓名),OU=(组织单位名称),O=(组织名称),L=(城市名称),ST=(省),C=(国家)" -storetype (密钥仓库类型) -keyalg (生证书的算法名称) -keysize (密钥长度,证书大小) -keystore (指定生成证书的位置和证书名称) -validity (证书有效期，天单位)
 *  keytool -genkey -alias uublog -dname "CN=wrh,OU=my,O=my,L=SH,ST=SH,C=CN" -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 9365
 * 获取名为keystore.p12的证书，将其放入resources目录中，并在application.properties或application.yml中配置
 * #https端口号.
 * server.port: 443
 * #证书的路径.
 * server.ssl.key-store: classpath:keystore.p12
 * #证书密码，请修改为您自己证书的密码.
 * server.ssl.key-store-password: 123456
 * #秘钥库类型
 * server.ssl.keyStoreType: PKCS12
 * #证书别名
 * #server.ssl.keyAlias: uublog
 *若指定的端口非443，则在访问的时添加具体的端口号。
 *
 *
 * 将http重定向到https  即为本示例代码
 *
 * @Date : 2022/9/29 14:55
 */
@Configuration
public class SSLUtils {

    @Bean
    public Connector connector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setSecure(false);
        connector.setPort(9988);           //
        connector.setRedirectPort(443);    // 跳转端口  这个要和server.port相匹配
        return  connector;
    }

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector) {
        TomcatServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory(){
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection securityCollection = new SecurityCollection();
                securityCollection.addPattern("/*");
                securityConstraint.addCollection(securityCollection);
                context.addConstraint(securityConstraint);
            }
        };
        webServerFactory.addAdditionalTomcatConnectors(connector);
        return webServerFactory;
    }
}
