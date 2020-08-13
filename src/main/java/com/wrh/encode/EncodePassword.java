package com.wrh.encode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname EncodePassword
 * @Description TODO
 * @Date 2020/8/4 15:29
 */
@Slf4j
public class EncodePassword {

    private static final String rsaPrivate = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK83vH4Ff8UxrJTu8CzYV4qgImKZCq82T6hPgNsqfG+wv4Am9EaC1Z/i2ijgrwrnh8RxQen54XZS2WJk3M8cwbJc6BYMqwLKvDkr1hlM7IYaJdj43Va/1qSTewxXVieC8yYHAfiD/XlvPK1YYnW8E7jx7Igwn3c0NaEhiOH63g3fAgMBAAECgYBHz+QlEkPnohBFihhNiO14F2GAX5ENdoj3Rn5dVPBjJmGWOGDtdTnwqZ0YG94h7fqq/HAzYQKS6CnINeZ5zCNmNRxc4qGfhiqkDLaU7kjFi5bdbHHwUkip+hX+02VZ+rKOkx3l0ky7Fj1dPASw7OemvzWIa4JTfhMsIwfCI5dtAQJBANk2UsmVMYiUdmR1NX5txiaO3Ti2sKtFUVhtKQiq7pXNSCzqttvaql0c3eakRsj3bGEUz+XNVcckcfIQI2Re7v8CQQDOgat7hbpPXRMchYLP2fFuuaDLU8eggpGc4TLIIQLd2vEM3fKf6kIjPMoOQpPiwAFSFqpeAkpKbO0/rNkqjcEhAkEAwS+/kepPk9n3MoHMghXAFqsZtkdF+g48uxjbVgoxCKr7dneLCx8ARrBV67xP+I2WpwGLpidqEyQm89PGpo6IoQJAU++k0epDFisQr5Ec4HsHhSfyUXLWO0mXAhsO1wWD8sUhIUe9bH59L3Fv92fcUFgWsVUBHoDzzViE/lK1WnSPwQJAeJWkY0fnQm0fN2J88creGWCKxQBImjn+YBdksxN3Qg0/tAImoP8stiDw7kPX74zNkU+s+ZUJOICMCgwhVBE+BQ==";

    public static final String PASSWORD_KEY = "AHkufhui34G6839f";

    @Test
    public void Test15() throws Exception {
        String password = "123456";
        String encode ;
        String decodePassword ="";
        try{

            decodePassword = RSA.decrypt(password, rsaPrivate);
        }catch (Exception e){
            System.out.println("exception: " + e);
        }
        System.out.println(decodePassword);
        System.out.println(getEncryptPasswd(decodePassword));
    }




    public static String getEncryptPasswd(String passwd) {
        return Md5Utils.encrypt(passwd + PASSWORD_KEY);
    }
}
