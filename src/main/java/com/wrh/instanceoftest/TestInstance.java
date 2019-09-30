package com.wrh.instanceoftest;

import com.alibaba.fastjson.JSON;
import com.wrh.instanceoftest.vo.BoundDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:38 2019/9/26 0026
 * @Modified By:
 */
@Slf4j
public class TestInstance {
    public static void main(String[] args) {
        BoundDTO b1 = new BoundDTO();
        b1.setName("wrh");
        HashSet<String> s1 = new HashSet<>();
        s1.add("s1");
        s1.add("s2");
        s1.add("s3");
        b1.setCode(s1);

        log.info("===>before b1: {}", JSON.toJSONString(b1));
        handleInstance(b1);
        log.info("===>after b1: {}", JSON.toJSONString(b1));
    }

    private static void handleInstance(Object b1) {
        if(b1 instanceof BoundDTO){
            BoundDTO b2 = new BoundDTO();
            BeanUtils.copyProperties((BoundDTO) b1, b2);
            List<String> s1 = b2.getCode().stream().filter(e->e.equals("s2")).collect(Collectors.toList());
            HashSet<String> s2 = new HashSet<>(s1);
            b2.setCode(s2);
            ((BoundDTO) b1).setCode(s2);

        }
    }
}
