package com.wrh.preview.liantong;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:52 2018/8/27 0027
 * @Modified By:
 */
public class TestTelRemove {

    public static void main(String[] args) {
        String tel  = "13412345678";
        byte[] telb = tel.getBytes();
        String newTel = "****";
        byte[] newTelB = newTel.getBytes();
        System.arraycopy(newTelB,0,telb,3,4);
        System.out.println("===>"+new String(telb));

        String phone = "13812345678";
        phone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
//        System.out.println("newPhone===>" + newPhone);
        System.out.println("phone===>" + phone);



    }
}
