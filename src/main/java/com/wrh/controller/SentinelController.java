package com.wrh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuruohong
 * @Classname SentinelController
 * @Description TODO
 * @Date 2021/8/11 20:54
 */
@RestController
@RequestMapping("/sentinel/")
@Slf4j
public class SentinelController {

    /**
     * 测试限流
     * @return
     */
    @RequestMapping("test")
//    @SentinelResource(value = MySentinelResource.FLOW_RESOURCE, blockHandler = "testBlockHandler")
    public String test(){
        log.info("SentinelController---test......");

        return "test ok !";
    }

    /**
     * 限流后处理方法
     * @param e
     * @return
     */
    /*public String testBlockHandler(BlockException e){
        log.error("限流异常了：{}", e);
        return "error，限流了！";
    }*/
}
