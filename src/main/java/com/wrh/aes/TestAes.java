package com.wrh.aes;

import com.zcsmart.aes.IAES;
import com.zcsmart.aes.en.AESModule;
import com.zcsmart.aes.impl.AES192Impl;
import org.apache.commons.codec.binary.Base64;

import java.util.HashSet;
import java.util.Set;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:28 2019/6/12 0012
 * @Modified By:
 */
public class TestAes {

    private static String key = "zcsmart.key";
    private static String iv = "zcsmart.iv";

    private static String encData = "jnnKuttGR0wPoypxxmsIyOC88JP6qafvZHFUk9T31f2BkexryTlya35nU1wXDKDOvKnj2cO6WVPdpA8ncFOtl3V9nnxgzTDLat5Vt++0vVft3DKbQHE/r+FPY7fMEa9iBoh5mOyBrDa/xeIseVHaW9AADPNVbvWucYsbTFO/T04DeWvpFLmrogYtcfNwcEPtSs2B8/6ppSiv0bH2NtmE7Gnu6cv5w7Z04kPp5k+1Spv9jkELI4GXzYUQQ1CcM8is";
//    private static String encData = "jnnKuttGR0wPoypxxmsIyOC88JP6qafvZHFUk9T31f2BkexryTlya35nU1wXDKDOvKnj2cO6WVPdpA8ncFOtl3V9nnxgzTDLat5Vt++0vVft3DKbQHE\\/r+FPY7fMEa9iBoh5mOyBrDa\\/xeIseVHaW9AADPNVbvWucYsbTFO\\/T04DeWvpFLmrogYtcfNwcEPtSs2B8\\/6ppSiv0bH2NtmE7Gnu6cv5w7Z04kPp5k+1Spv9jkELI4GXzYUQQ1CcM8is";

    private static String encData1 = "jnnKuttGR0wPoypxxmsIyOC88JP6qafvZHFUk9T31f2BkexryTlya35nU1wXDKDOvKnj2cO6WVPdpA8ncFOtl3V9nnxgzTDLat5Vt++0vVft3DKbQHE\\/r+FPY7fMEa9iBoh5mOyBrDa\\/xeIseVHaW1vTMN9bCPFuGm+VjbWMPUAOAqlIgn6uU4XKcqsZXNxFglOjpUQHlA5g7tHSdi9R7XVK4Kac2xirnpRWGDA+CKpMCi8N3LGQhMYxS47lNv2Q";
    private static String resp = "J3FxZzIlKh0l2jeiVECW/3i96jGe7SUIoxRR3JmHiZsclIrGpfl1HKadpy2dzKoHsN6ebn7wMCs7SB86yfLxkRrbWIr31PR6ba0oHXcMzGbvNkZnbyCrWCQ420+ZU9ktAWQwnbDmRQpzezOVq2A7CF2js0mHpb4aKOX/P7b6IJXZ01rOFeAGGnqnF1VNGnBfEH6Yl/knvZ+1LeqKNzzsz8ONGYjKanpHfSID7mPBllLkoQPQxUvR38EJgF6ofJmSBysfxceJELc4oQZrswOjzigImD/uRy+1wQzakPp6YErd7aAm1K+G0mNoZ2qLUeH0BcSQQTeUI0XyatWnVO4/haevS6UVMg/vBWqXWt3drE/2qtkrPotTvz59jUS4qrCuMowg9ESptoQPLgQg8ZCSGga5c8oLfQQozPHsfrd3mlaHMokiOHjYLhzKZ43rWCvyjl7K8StMqemdqu9WTM40GKa2FMmetW7bQXu5Rxzcb7FX85Zgb0yUUngd1PiG7Z5mb4DPzvo0x1svq7roAGG+0LGjjA9Tto1wcIaIlAfVlmIhRcZW3FBpmmbCBYdAcugjeMumF8NynVGZpeGBi3a3LWquYpmBkXnnOZGC4+R9oZrFGgRL2SyJ8pf5xu9hlY8cmu8oqH88fplqAS+BMs9rLIsxjGddMFUip670zW/lODqYcUcfb9ifSKFA9Ulb8rXhEkX/b2jZRAt01pz2HN/5fEzGKtstPU3du61NLm3XLbPAnBE5qFwtUBIsivmNam2oXUmZ6nYD32YXSiR3lB11MU+Yf8OnH/g8W60n+dmV9uY9As39ATPSBWCnuNj29RblSzsZZC4VK4n4vPJGOk9XcI5CIH9BTzrYl1WbBcYsky/RL2+ci9C03org7y43m66M3t8pMFa4RkdM6gEcRQq2iRDNVToPeUNgxqGKb4zkir++5uJ+MoRYNImAwnTogyNuJOqppgW3PQzAicTX+rObkSNaxayiBpkFMAqA2mC2dtzEVxdhrSbJUuJaKCMPP0ytMLfjxNYz20LL+oZkaZtdegrlrj5WSWDi4ZWObtKUypmC13tl+sQPwlotlrEb6L1txXFVVEmNrCMeL3sz2RGnng==";

    public static void main(String[] args) {
        IAES iaes = new AES192Impl();


        String str = "{}";
//        String encData = "jnnKuttGR0wPoypxxmsIyOC88JP6qafvZHFUk9T31f2BkexryTlya35nU1wXDKDOvKnj2cO6WVPdpA8ncFOtl3V9nnxgzTDLat5Vt++0vVft3DKbQHE/r+FPY7fMEa9iBoh5mOyBrDa/xeIseVHaW9AADPNVbvWucYsbTFO/T04DeWvpFLmrogYtcfNwcEPtSs2B8/6ppSiv0bH2NtmE7Gnu6cv5w7Z04kPp5k+1Spv9jkELI4GXzYUQQ1CcM8is";
        byte[] sha256Byte = SHA256Util.getServerHash_C(encData);
        String sha256_str = Base64.encodeBase64String(sha256Byte);
        System.out.println("body体的hash_body值(): " + sha256_str);


        String auth = "{\"companyId\": \"8054\",\"clientid\":\"6\",\"timestamp\":\"20190505134257\",\"hashbody\": \"QjFBNUFBMjJBRjZFMzM2RkZGMEQzMUUwOEIwQzA2N0QyMjYxRkNFNDlDM0RCMjkwREVDRDJBOUEyRjUwMTIwQg==\",\"encType\":\"1\"}";

        System.out.println("认证数据: " + auth);

        String signData = iaes.signData(auth);
        System.out.println("签名数据: " + signData);

        String plain = iaes.decDataBase64(resp, AESModule.AES_192_CBC_PKCS7, key, iv);
        System.out.println("解密明文: " + plain);

        Set<String> set = new HashSet<>();
        System.out.println("init set size :"+set.size());


    }
}
