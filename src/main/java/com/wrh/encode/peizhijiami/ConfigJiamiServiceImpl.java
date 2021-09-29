package com.wrh.encode.peizhijiami;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuruohong
 * @Classname ConfigJiamiSearceImpl
 * @Description TODO
 * @Date 2021/9/8 16:27
 */
@Service
public class ConfigJiamiServiceImpl implements ConfigJiamiService {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Override
    public String jiami(String jiami) {
        String encryptStr = stringEncryptor.encrypt(jiami);
        System.out.println("encryptStr = " + encryptStr);
        return encryptStr;
    }

    @Override
    public String jiemi(String jiemi) {
        return stringEncryptor.decrypt(jiemi);
    }
}
