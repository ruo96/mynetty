package com.wrh.BooleanDefault;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:18 2019/9/4 0004
 * @Modified By:
 */
@Slf4j
public class TestBooleanDefault {
    
    @Test
    public void test(){
        BooleanVo vo = new BooleanVo();
        vo.setData("123");
        vo.setHide("456");
        vo.setFly(false);
        log.info("=== result is :{}", JSON.toJSONString(vo));

        BooleanVo vo1 = new BooleanVo();
        vo1.setIsData(false);
        vo1.setData("123");
        vo1.setHide("456");
        vo1.setFly(true);
        log.info("=== result is :{}", JSON.toJSONString(vo1));
        log.info("=== result is :{}", vo1);
    }
}
