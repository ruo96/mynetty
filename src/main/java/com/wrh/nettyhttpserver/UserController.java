package com.wrh.nettyhttpserver;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuruohong
 * @Classname UserController
 * @Description TODO
 * @Date 2022/2/28 20:19
 */
@RestController
public class UserController{

    @RequestMapping(value = "/login",produces = "text/json;charset=utf-8")
    public String login(String username,String pwd){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>now login");
        JSONObject resultJson = new JSONObject();
        Map<String, String> loginResult = new HashMap<String, String>();
        loginResult.put("username", username);
        loginResult.put("age", "20");
        loginResult.put("sex", "boy");

        resultJson.put("code", 200);
        resultJson.put("msg", "登录成功");
        resultJson.put("result", loginResult);

        return JSONObject.toJSONString(resultJson);
    }
}
