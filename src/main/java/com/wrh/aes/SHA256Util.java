package com.wrh.aes;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by garychen on 2017/12/12.
 */
public class SHA256Util {

    public static String getServerHash(String encDataStr){
        // 将 byte[] 转换为 string
        StringBuffer strHexString = new StringBuffer();
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            // 计算获得hash
            byte[] hashByteBuffer = sha256.digest(encDataStr.getBytes(Charset.forName("UTF-8")));
            // 遍历 byte buffer
            for (int i = 0; i < hashByteBuffer.length; i++)
            {
                String hex = Integer.toHexString(0xff & hashByteBuffer[i]);
                if (hex.length() == 1)
                {
                    strHexString.append('0');
                }
                strHexString.append(hex);
            }
        }catch (Exception e){
        }
        // 得到返回結果
        String strResult = strHexString.toString();
        return strResult;
    }

    public static byte[] getServerHash_C(String encDataStr){
        byte[] plainText = encDataStr.getBytes();

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(plainText);
        byte[] digest = messageDigest.digest();
        return digest;
    }
}
