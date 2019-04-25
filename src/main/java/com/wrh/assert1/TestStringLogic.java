package com.wrh.assert1;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:19 2019/4/11 0011
 * @Modified By:
 */

public class TestStringLogic {
    public static void main(String[] args) {
        /*String a = "";
        String b = "  ";

        String c = null;
        String d = "123";

        if(StringUtils.isBlank(a)){
            System.out.println("a : {}");
        }
        if(StringUtils.isBlank(b)){
            System.out.println("b : {}");
        }
        if(StringUtils.isBlank(c)){
            System.out.println("c : {}");
        }
        if(StringUtils.isBlank(d)){
            System.out.println("d : {}");
        }*/

        /*int i = 20;
        while(i >0){
            System.out.println("执行了一次");
            i--;
        }
        System.out.println("执行完毕！");*/

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        System.out.println(time);
        System.out.println(LocalDateTime.now());
    }
}
