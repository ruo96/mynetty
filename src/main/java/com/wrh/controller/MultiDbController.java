package com.wrh.controller;

import com.wrh.controller.multidb.DbsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuruohong
 * @Classname MultiDbController
 * @Description TODO
 * @Date 2022/1/13 18:01
 */
@Slf4j
@RestController
public class MultiDbController {

    @Autowired
    private DbsProperties dbsProperties;

    @GetMapping("/multi/get")
    public String getMultiDbValues() {
        log.info("[multidb]>>> value: {}", dbsProperties.getDbs());
        return "ok";
    }
}
