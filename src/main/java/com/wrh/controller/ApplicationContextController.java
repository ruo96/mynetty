package com.wrh.controller;

import com.wrh.annotate.annotation.PassportTotal;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author wuruohong
 * @Classname HelloController
 * @Description TODO
 * @Date 2020/2/27 11:32
 */
@Slf4j
@RestController
@PassportTotal
@RequestMapping("applicationcontext/")
public class ApplicationContextController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextController.class);



    @RequestMapping(value = "bean1")
    public String bena1(HttpServletRequest request) {
        logger.info(">>>bena1 time: {}", LocalDateTime.now());
        //直接通过方法中的HttpServletRequest对象
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        //Wolf1Bean wolf1Bean = (Wolf1Bean)applicationContext.getBean("wolf1Bean");
        return "success";
    }

    @RequestMapping(value = "bean2")
    public String bena2() {
        logger.info(">>>bena1 time: {}", LocalDateTime.now());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//手动获取request对象
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());

        //Wolf2Bean wolf2Bean = (Wolf2Bean)applicationContext.getBean("wolf2Bean");
        //return wolf2Bean.toString();
        return "success";
    }


}
