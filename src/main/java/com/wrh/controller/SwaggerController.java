package com.wrh.controller;

import com.wrh.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author wuruohong
 * @Classname SwaggerController
 * @Description TODO
 * @Date 2020/12/17 15:58
 */
@Slf4j
@RestController
@Api(tags="swaggercontroller 接口")
public class SwaggerController {

    @ApiOperation(value = "查询接口1", notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "username",value = "用户名",required = true, defaultValue = "wrh"),
            @ApiImplicitParam(paramType = "path", name = "age",value = "年龄",required = true, defaultValue = "18")
    })
    @RequestMapping("/swagger2")
    public String swagger2(HttpServletRequest request){

        String username = request.getParameter("username");
        String age = request.getParameter("age");

        return "ok";
    }
}
