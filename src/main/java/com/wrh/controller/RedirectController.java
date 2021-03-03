package com.wrh.controller;

import com.wrh.annotate.annotation.PassportTotal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author wuruohong
 * @Classname HelloController
 * @Description TODO
 * @Date 2020/2/27 11:32
 */
@Slf4j
//@RestController
@PassportTotal
@Controller
public class RedirectController {


    @PostMapping(value = "/order")
    public String order(HttpServletRequest req)  {
        log.info(">>> /order  time: {}", LocalDateTime.now());
        FlashMap flashMap = (FlashMap) req.getAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE);
        flashMap.put("name","wrh");
        return "redirect:/orderlist";
    }

    @GetMapping(value = "/orderlist")
    @ResponseBody
    public String orderList(HttpServletRequest req)  {
        log.info(">>> /orderlist  time: {}", LocalDateTime.now());
        String value = (String) req.getAttribute("name");
        log.info(">>> /orderlist  value: {}", value);
        return value;
    }

    @PostMapping(value = "/order2")
    public String order2(RedirectAttributes attr)  {
        log.info(">>> /order2  time: {}", LocalDateTime.now());
        attr.addFlashAttribute("site", "www.baidu.com");
        attr.addAttribute("name","wrh");
        return "redirect:/orderlist2";
    }

    @GetMapping(value = "/orderlist2")
    @ResponseBody
    public String orderList2(Model model)  {
//        log.info(">>> /orderList2  time: {}", LocalDateTime.now());
////        String value = model.getA;
//        log.info(">>> /orderList2  value: {}", value);
//        return value;
        return "";
    }


}
