package com.wrh.lambda.filterUse;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 10:04 2019/9/6 0006
 * @Modified By:
 */
@Slf4j
public class TestFilter {

    @Test
    public void test(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        List<Integer> list1 = getList(list);
        log.info("list: {}" , JSON.toJSONString(list1));
    }

    private List<Integer> getList(List<Integer> list){
        return list.stream().filter(e-> e > 5).collect(Collectors.toList());
    }
}
