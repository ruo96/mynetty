package com.wrh.controller.decodeencode;

import com.wrh.annotate.annotation.decodeencode.Encrypt;
import com.wrh.controller.response.RespBean;
import com.wrh.elasticsearch.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuruohong
 * @Classname EncodeController
 * @Description TODO
 * @Date 2021/3/22 15:37
 */
@Slf4j
@RestController
@RequestMapping("encode/")
public class EncodeController {

    @RequestMapping("encode")
    @Encrypt
    public RespBean encode() {
        Student s = new Student();
        s.setName("w1");
        s.setGrade(100);
        return RespBean.ok("suc", s);
    }
}
