package com.wrh.config.druid;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname MyPasswordGen
 * @Description TODO
 * @Date 2021/3/12 10:58
 */
public class MyPasswordGen {
    public static void main(String[] args) throws Exception {
        String password = "mypassword";
        ConfigTools.main(new String[]{password});
    }


    /**
     * privateKey:MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAmsxDz/dJqdpF66QgPoWKgdyCtUGlb9YLh16eymVPetC7X7NAPWi4UHWb4H7OZhzWCkmZBqUc+VRO2VqTyII1EQIDAQABAkBNgaUahei+QOnteHokk9yr9OsFMl9RRczz9K5xDJTlGr7YfjK8iuzA8qo89yeiGWWY6zMqDOU4QbGwYtA87yxZAiEA8oDzTMWEyKXlX+J6cUDfyZEsqpOlzl1pkjww21XyDkMCIQCjabvqdaVrikz9yRyXPdokn81u2loLdb6gkxRW4iE8GwIgSMTtPXJgAB3YEMfTWjzPapsHvkAEF0LstRceyhKmSiUCIEYC0N28laijUiUQ+szZVRKH3I33wshSI9/L49TtgOutAiBfqgZvTK7llrqtJlwHqRX5VgjOvTRlfD9oFs6AqA4vtQ==
     * publicKey:MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJrMQ8/3SanaReukID6FioHcgrVBpW/WC4densplT3rQu1+zQD1ouFB1m+B+zmYc1gpJmQalHPlUTtlak8iCNRECAwEAAQ==
     * password:XSsYBuCPY3S6I7TQFIIbawau9SLZTpkcMqFTqw+2L05zlQ674vZDI8OjvDBrZ+2ih1wkGYgC67Wrkid99u3+mQ==
     *
     * 从上述结果可以看出，使用 ConfigTools 类会生成 3 部分的内容：
     * privateKey：私钥，暂时不会用到，用于密码的加密；
     * publicKey：公钥，用于密码的解密；
     * password：加密之后的密码。
     *
     * 完成了以上操作之后，只需要将上一步生成的公钥和密文添加到项目的配置文件 application.yml（或application.xml）
     * password为上面的password
     *
     * spring:
     *   # MySQL 配置
     *   datasource:
     *     driver-class-name: com.mysql.cj.jdbc.Driver
     *     type: com.alibaba.druid.pool.DruidDataSource
     *     druid:
     *       url: jdbc:mysql://127.0.0.1:3306/testdb?serverTimezone=Asia/Shanghai&characterEncoding=UTF-8&useSSL=false
     *       username: root
     *       password: IMgKm27bOHok3/+5aDL4jGBoVVZkpicbbM6pIXQppi3dI7h3jngSAqhqwqYnfuYpyVJ0k++q9xWWnHtd6sAWnQ==
     *       # encrypt config
     *       filters: config
     *       connect-properties:
     *         config.decrypt: true
     *         config.decrypt.key: ${spring.datasource.druid.publickey}
     *
     * java -jar xxx.jar --spring.datasource.druid.publickey=你的公钥
     */


    @Test
    public void Test51() throws Exception {
        String password = "XSsYBuCPY3S6I7TQFIIbawau9SLZTpkcMqFTqw+2L05zlQ674vZDI8OjvDBrZ+2ih1wkGYgC67Wrkid99u3+mQ==";
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJrMQ8/3SanaReukID6FioHcgrVBpW/WC4densplT3rQu1+zQD1ouFB1m+B+zmYc1gpJmQalHPlUTtlak8iCNRECAwEAAQ==";
        String result = ConfigTools.decrypt(publicKey, password);
        System.out.println("result = " + result);  /*result = mypassword*/

    }
}
