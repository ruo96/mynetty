package com.wrh.controller.intercept;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static com.google.common.base.Charsets.UTF_8;

/**
 * 摘要计算
 *
 * @author zing
 * @since 2017-12-3 14:14:31
 */
public final class Digest {

    private static Logger logger = LoggerFactory.getLogger(Digest.class);

    private Digest() {
    }

    /**
     * 生成MD5 摘要
     *
     * @param source 源数据
     * @return MD5摘要
     */
    public static String md5TrimZero(String source) {
        logger.info("md5TrimZero:{}", source);
        String result = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(source.getBytes(UTF_8));
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            result = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException", e);
            result = StringUtils.EMPTY;
        }
        return result;

    }

    /**
     * 保证32位返回
     *
     * @param source
     * @return
     */
    public static String md5(String source) {
        return StringUtils.leftPad(md5(source), 32, '0');
    }


    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText 内容
     * @return 结果
     */
    public static String sha256(final String strText) {
        return sha(strText, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param strText 内容
     * @return 结果
     */
    public static String sha512(final String strText) {
        return sha(strText, "SHA-512");
    }

    /**
     * 字符串 SHA 加密
     *
     * @param strText
     * @param strType
     * @return
     */
    private static String sha(final String strText, final String strType) {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (StringUtils.isNotEmpty(strText)) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果
                byte[] byteBuffer = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuilder strHexString = new StringBuilder();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                logger.error("NoSuchAlgorithmException", e);
                strResult = StringUtils.EMPTY;
            }
        }

        return strResult;
    }


    /**
     * Base64加密
     *
     * @author lance
     */
    public static String encodeBase64(final String password) {
        return StringUtils.isBlank(password) ? "" : Base64.getEncoder().encodeToString(password.getBytes());
    }

    /**
     * Base64解密
     *
     * @author lance
     */
    public static String decodeBase64(final String password) {
        return StringUtils.isBlank(password) ? "" : new String(Base64.getDecoder().decode(password));

    }


    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    public  static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }
    /**
     * sha256_HMAC加密
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */
    public static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }

}
