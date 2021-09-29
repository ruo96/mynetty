package com.wrh.jiamijiemi.feiduichen;

import com.wrh.utils.Base64;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.crypto.Cipher;
import java.security.*;

/**
 * @author wuruohong
 * @Classname GenerateKeyPair
 * @Description TODO
 * @Date 2021/7/2 18:19
 */
@Slf4j
public class GenerateKeyPair {
    @Test
    public void Test14() throws NoSuchAlgorithmException {
        KeyPair pair = genKeyPair();
        System.out.println("pair pub= " + pair.getPublic());
        System.out.println("pair pri= " + pair.getPrivate().getAlgorithm());
    }

    public KeyPair genKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    // 加密
    public static byte[] encrypt(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(message.getBytes("utf-8"));
    }

    // 解密
    public static byte[] decrypt(PrivateKey privateKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(encrypted);
    }

    /**
     * 使用RSA签名
     */
    private static String signWithRSA(String content, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initSign(privateKey);
        signature.update(content.getBytes("utf-8"));
        byte[] signed = signature.sign();
//        return base64Encode(signed);
        return Base64.encode(signed);
    }

    /**
     * 使用RSA验签
     */
    private static boolean checkSignWithRSA(String content, PublicKey publicKey,String sign) throws Exception {
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initVerify(publicKey);
        signature.update(content.getBytes("utf-8"));
        return signature.verify(Base64.decode(sign));
    }
    
    @Test
    public void Test71() throws Exception {
        KeyPair keyPair = genKeyPair();

        byte[] encryptData = encrypt(keyPair.getPublic(), "不学无数");

        System.out.println(String.format("加密后的数据：%s",Base64.encode(encryptData)));

        System.out.println(String.format("解密后的数据：%s",new String(decrypt(keyPair.getPrivate(),encryptData),"utf-8")));

        String context = "加签的字符串";

        String sign = signWithRSA(context, keyPair.getPrivate());

        System.out.println(String.format("生成的签名：%s",sign));

        Boolean checkSignWithRSA = checkSignWithRSA(context, keyPair.getPublic(), sign);

        System.out.println(String.format("校验的结果：%s",checkSignWithRSA.toString()));
        
    }
}
