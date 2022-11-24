package com.wrh.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : wuruohong
 * @description :
 * @Date : 2022/11/23 18:00
 */
@Slf4j
@RestController
@RequestMapping("test123/")
public class TestController {
    public final static String SEND_TYPE_ALL = "ALL";
    public final static String SEND_TYPE_ALONE = "ALONE";
    @Autowired
    SocketUtil socketUtil;

    @RequestMapping("msg")
    public String testSendMsg(@RequestBody MyMessage myMessage){

        log.info("[sendMsg]>>> get msg: [{}]", myMessage);
        Map<String, Object> map = new HashMap<>();
        map.put("msg",myMessage.getContent());

        //群发
        if (SEND_TYPE_ALL.equals(myMessage.getType())){
            socketUtil.sendToAll( map,myMessage.getChannel());
            return "success";
        }
        //指定单人
        if (SEND_TYPE_ALONE.equals(myMessage.getType())){
            socketUtil.sendToOne(myMessage.getTo(), map, myMessage.getChannel());
            return "success";
        }

        return "fail";
    }

    @RequestMapping("msg2")
    public String testSendMsg2(@RequestBody Map<String, String> myMessage){

        log.info("[sendMsg]>>> get msg: [{}]", myMessage);
        Map<String, Object> map = new HashMap<>();
        map.put("msg",myMessage.get("content"));

        //群发
        if (SEND_TYPE_ALL.equals(myMessage.get("type"))){
            socketUtil.sendToAll( map,myMessage.get("channel"));
            return "success";
        }
        //指定单人
        if (SEND_TYPE_ALONE.equals(myMessage.get("type"))){
            socketUtil.sendToOne(myMessage.get("to"), map, myMessage.get("channel"));
            return "success";
        }

        return "fail";
    }
}
