package com.wrh.aes;

import com.zcsmart.aes.IAES;
import com.zcsmart.aes.en.AESModule;
import com.zcsmart.aes.impl.AES192Impl;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:21 2019/6/14 0014
 * @Modified By:
 */
public class GetDecData {

    private static String key = "zcsmart.key";
    private static String iv = "zcsmart.iv";

    private static String body = "KzYZ9vup\\/ToEhz6tQRBnraenMsEJB4FnVX7dEmj57FxTZvVZmprAseyrgSTE52i3nKDG90BkAn4NU+MhpeqYB+nnHnB94XqJJ8tV1WPgRUYwdSoPfV5bQMHU2IUtv4p4t3GXvQV9HzaWbV\\/ELuwqfoTXnTGdQa3u0cC5K1seVmFCWNd8rpM0Zh7fawS9v97XB3uYHZmzAXFtjfNj\\/D7MBXdeiVfEFeyYjTOx9rlTQSMwWaOZEbRVvuk3xQSxor7hH\\/ODfbcQ0ylx1P9eDMc6VA==";


    public static void main(String[] args) {
        IAES iaes = new AES192Impl();
        String plain = iaes.decDataBase64(body, AESModule.AES_192_CBC_PKCS7, key, iv);
        System.out.println("解密明文: " + plain);
    }
}
