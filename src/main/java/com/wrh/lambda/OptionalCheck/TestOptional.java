package com.wrh.lambda.OptionalCheck;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Optional;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:28 2019/9/27 0027
 * @Modified By:
 */
@Slf4j
public class TestOptional {

    @Test
    public void testOptional(){
        String nullString = null;
        String notNullString = "123";
        Optional<String> o1 = Optional.ofNullable(notNullString);

        o1.ifPresent(e->{
            log.info("{} not null",e);
        });

        Optional<String> o2 = Optional.ofNullable(nullString);

        String newO2 = o2.map(e->"good " + e + "!").orElse("null!");
        String newO3 = o2.map(e->"good " + e + "!").orElseGet(()->"notNull");
        log.info("newO2 is: {}",newO2);
        log.info("newO3 is: {}",newO3);

    }
}
