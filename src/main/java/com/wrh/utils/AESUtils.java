package com.wrh.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES对称加密和解密，有偏移量
 * @author
 * @date
 */
public class AESUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtils.class);

    // 密匙
    private static final String KEY = "f5k9f5w7f8g4er26";
    // 偏移量
    private static final String OFFSET = "6e8y6w45ju8w9jq8";
    // 编码
    private static final String ENCODING = "UTF-8";
    //算法
    private static final String ALGORITHM = "AES";
    // 默认的加密算法
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    
    
    /**
     * 加密
     * @param data
     * @return
     * @throws Exception
     * @return String
     * @author tyg
     * @date   2018年6月28日下午2:50:35
     */
    public static String encrypt(String data)  {
        String result="";
        try{
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("ASCII"), ALGORITHM);
            //使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec(OFFSET.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(data.getBytes(ENCODING));
            //此处使用BASE64做转码。
            result= Base64.encode(encrypted);
        }catch(Exception e){
            LOGGER.error("[aes]>>> encrypt error  req: {}  e:{}", data, e);
            return "";
        }
        return result;
    }
    
    /**
     * 解密
     * @param data
     * @return
     * @throws Exception
     * @return String
     * @author tyg
     * @date   2018年6月28日下午2:50:43
     */
    public static String decrypt(String data)  {
        String origin ="";
        try{
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("ASCII"), ALGORITHM);
            //使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec(OFFSET.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] buffer = Base64.decode(data);
            byte[] encrypted = cipher.doFinal(buffer);
            origin = new String(encrypted, ENCODING);
        }catch(Exception e){
            LOGGER.error("[aes]>>> decrypt error  req: {}  e:{}", data, e);
            return "";
        }
        return origin;
    }
}