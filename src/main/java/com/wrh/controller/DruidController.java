package com.wrh.controller;

import com.wrh.controller.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wuruohong
 * @Classname FileUploadController
 * @Description TODO
 * @Date 2020/12/8 12:02
 */
@Slf4j
@RestController
public class DruidController {

    @Autowired
    TestMapper testMapper;


    @RequestMapping("/count")
    public String findCount(HttpServletRequest req){
        log.info(">>> findcount ：{}", LocalDateTime.now());
        Integer count = testMapper.findCount();
        return String.valueOf(count);

    }
}
