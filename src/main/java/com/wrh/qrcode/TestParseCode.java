package com.wrh.qrcode;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 12:42 2019/6/15 0015
 * @Modified By:
 */
@Slf4j
public class TestParseCode {
    public static void main(String[] args) {

        String code = "513010AehW4AAeJnxx8ZC9AAAgAASw7pEQ...gk6Vebv_1O3TVtWZbQCr";
//        String code = "NTEyMDEwAAEABwAAAhVGGQVyQZNhAAAAAQQGiTGEzohJ4uxK3tk48qYg6wIY1JvfneV07aTm";

        CodeInfo codeInfo = null;
        Set<String> set = new HashSet<>();
        try {
            for (int i = 0; i < 1 ; i++) {

                codeInfo = CodeParserFactory.parseCode(code);
                set.add(codeInfo.getCodeId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("===> {}",codeInfo);




    }
}
