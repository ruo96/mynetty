package com.wrh.controller;

import com.wrh.annotate.annotation.Student;
import com.wrh.domain.User;
import com.wrh.domain.ValidationGroup1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuruohong
 * @Classname ValidationController
 * @Description TODO
 * @Date 2020/12/17 16:21
 */
@Slf4j
@RestController
@Api(tags="入参校验 接口")
public class ValidationController {

    @ApiOperation(value = "插入用户信息", notes = "插入")
    @PostMapping("/validate")
    public List<String> swagger2(@Validated(ValidationGroup1.class) @RequestBody User user, BindingResult result){

        List<String> errors = new ArrayList<>();
        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            allErrors.forEach(e->{
                errors.add(e.getDefaultMessage());
            });
        }
        log.info(">>> validate: {}", errors);
        return errors;
    }
}
