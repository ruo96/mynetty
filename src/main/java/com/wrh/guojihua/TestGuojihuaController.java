package com.wrh.guojihua;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:56 2019/4/3 0003
 * @Modified By:
 */
@Slf4j
@RestController
public class TestGuojihuaController {
    public static void main(String[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        log.info("locale is : {}",locale);
    }

    @RequestMapping(value = "/locale")
    public void testController(@RequestBody String req){
        Locale locale = LocaleContextHolder.getLocale();
        log.info("locale is : {}",locale);
    }
}
