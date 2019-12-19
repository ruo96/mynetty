package com.wrh.aes;

//import com.zcsmart.aes.IAES;
//import com.zcsmart.aes.en.AESModule;
//i/mport com.zcsmart.aes.impl.AES192Impl;
import org.apache.commons.codec.binary.Base64;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:15 2019/6/14 0014
 * @Modified By:
 */
public class GetEncData {

    private static String key = "zcsmart.key";
    private static String iv = "zcsmart.iv";

    private static String body = "{\"companyId\": 8054, \"pageIndex\": 1,\"pageSize\": 2}";



    /*public static void main(String[] args) {

        IAES iaes = new AES192Impl();
        String str = "";


        String encData = iaes.encDataBase64(body, AESModule.AES_192_CBC_PKCS7, key, iv);
        System.out.println("加密明文:" + str);
        System.out.println("encBody: " + "{\"encData\":\"" + encData + "\"}");

        //hash_body
        byte[] sha256Byte = SHA256Util.getServerHash_C(encData);
        String sha256_str = Base64.encodeBase64String(sha256Byte);
//        String sha256_str = Base64.encodeBase64URLSafeString(sha256Byte);
        System.out.println("body体的hash_body值(): " + sha256_str);

        String auth = "{\"companyId\":\"8054\",\"clientid\":\"6\",\"timestamp\":\"20191018101757\",\"hashbody\":\"" + sha256_str + "\"}";

        System.out.println("认证数据: " + auth);
        String signData = iaes.signData(auth);
        System.out.println("签名数据: " + signData);

        String plain = iaes.decDataBase64(encData, AESModule.AES_192_CBC_PKCS7, key, iv);
        System.out.println("解密明文: " + plain);
    }*/
}
