package com.wrh.collection.map.map;

import com.alibaba.fastjson.JSON;
import com.wrh.collection.map.map.vo.MapObj;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:25 2019/11/22 0022
 * @Modified By:
 */
@Slf4j
public class TestMapOpr {

    @Test
    public void test(){
        List<MapObj> list = new ArrayList<>();
        MapObj obj1 = new MapObj();
        obj1.setName("w1");
        obj1.setCar("bmw");
        obj1.setAge(18);

        MapObj obj2 = new MapObj();
        obj2.setName("w2");
        obj2.setCar("benz");
        obj2.setAge(20);

        list.add(obj1);
        list.add(obj2);

        log.info("===> list is : {}", JSON.toJSONString(list));

        Map<String, String> map = list.stream().collect(Collectors.toMap(MapObj::getName,MapObj::getCar));

        log.info("===> map is : {}", JSON.toJSONString(map));

    }
}
