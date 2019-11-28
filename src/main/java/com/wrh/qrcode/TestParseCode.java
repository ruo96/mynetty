package com.wrh.qrcode;

import com.alibaba.fastjson.JSON;
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

        String code = "513010AATpYAACGwwAdBimEAAgAASMQAAAiEAI55Vdwer8jx6TO2yDpq-9gXkC-LfS";

        CodeInfo codeInfo = null;
        Set<String> set = new HashSet<>();
        try {
            for (int i = 0; i < 1 ; i++) {

                codeInfo = CodeParserFactory.parseCode(code);
                log.info(" codeInfo: {}", JSON.toJSONString(codeInfo));
//                set.add(codeInfo.getCodeId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("===> {}",codeInfo);




    }
}
