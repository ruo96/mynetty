package com.wrh.annotate.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:40 2019/11/11 0011
 * @Modified By:
 */
@Slf4j
@RestController
public class AnnotationController {

    @PostMapping("/student")
    public String addStudent(@Valid @RequestBody Student student){
        return "student created";
    }

    @PostMapping("/studentwithmsg")
    public String addStudentWithMsg(@Valid @RequestBody Student student, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return bindingResult.getAllErrors().get(0).getDefaultMessage();
        }
        log.info("===>  controller over");
        return  "student created";
    }
}
