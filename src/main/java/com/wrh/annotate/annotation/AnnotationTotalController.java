package com.wrh.annotate.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:40 2019/11/11 0011
 * @Modified By:
 */
@Slf4j
@PassportTotal
@RestController
public class AnnotationTotalController {


    @Passport
    @PostMapping("/total")
    public String testAnoatation(@Valid @RequestBody String req){

        log.info("===>  enter withanonation :{}",req);
        return  "good";
    }
}
