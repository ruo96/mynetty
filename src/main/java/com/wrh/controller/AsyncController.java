package com.wrh.controller;

import com.wrh.controller.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuruohong
 * @Classname AsyncController
 * @Description TODO
 * @Date 2021/4/6 11:25
 */
@RestController
@Slf4j
@RequestMapping("/async/")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("get")
    public String getAsyncMsg() {
        log.info(">>> async controller begin");
        long start = System.currentTimeMillis();
        asyncService.getAsyncMsg();
        long end = System.currentTimeMillis();
        log.info(">>> controller begin:{} end:{} cost:{}", start, end, end-start);
        return "ok";

    }
}
