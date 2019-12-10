package com.wrh.basic.service;

import com.alibaba.fastjson.JSON;
import com.wrh.basic.AVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:37 2019/11/12 0012
 * @Modified By:
 */
@Service
@Slf4j
public class BasicServiceImpl implements BasicService {

    @Value("${cq.key}")
    private String key;

    @Override
    public String getBasic() {

        log.info(" service key: {}",key);

        AVo a = new AVo();
        a.setNum(1);
        a.setName("a");

        log.info("===>{}", JSON.toJSONString(a));
        a.setHash();
        log.info("===>{}", JSON.toJSONString(a));

        return JSON.toJSONString(a);
    }
}
