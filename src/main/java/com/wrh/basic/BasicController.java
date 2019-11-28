package com.wrh.basic;

import com.wrh.basic.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:35 2019/11/12 0012
 * @Modified By:
 */
@RestController
public class BasicController {

    @Autowired
    private BasicService basicService;

    @ResponseBody
    @PostMapping("/basic")
    public String getBasic(){

        return basicService.getBasic();
    }
}
