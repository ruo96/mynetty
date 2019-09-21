package com.wrh.list;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.wrh.lambda.basicuse.PersonModel;
import com.wrh.lambda.flatmapuse.Data;
import com.wrh.list.vo.EmptyList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:07 2019/9/19 0019
 * @Modified By:
 */
@Slf4j
public class TestEmptyList {

    @Test
    public void test(){
        EmptyList emptyList = new EmptyList();
        emptyList.setName("wrh");
        emptyList.setList(new ArrayList<>());
        log.info("=== result is :{}", JSON.toJSONString(emptyList));

        List<PersonModel> personModels = Data.getData();

        log.info("=== before result is :{}",JSON.toJSONString(personModels));

        personModels.stream().forEach(e->{
            if(e.getAge() == 20){
                e.setAge(99);
            }
        });
        log.info("=== before result is :{}",JSON.toJSONString(personModels));
    }


    @Test
    public void test1(){
        EmptyList emptyList = new EmptyList();
        if(StringUtils.isBlank(emptyList.getName())){
            log.info("empty");
            log.info("{}",emptyList.getName());
        }else {
            log.info(" not empty ");
        }

    }

    @Test
    public void test2(){
        EmptyList emptyList = new EmptyList();
        emptyList.setName("wrh");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        emptyList.setNumList(list);

        log.info("===> before : list: {}",JSON.toJSONString(emptyList));
        List<Integer> numList1 = emptyList.getNumList().stream().filter(e-> e > 2).collect(Collectors.toList());
        emptyList.setNumList(numList1);

        log.info("===> after : list: {}",JSON.toJSONString(emptyList));
        
        List<EmptyList> lists = new ArrayList<>();
        EmptyList list1 = new EmptyList();
        list1.setName("wrh");
        list1.setNumList(new ArrayList<>());

        EmptyList list2 = new EmptyList();
        list2.setName("rjj");
        list2.setList(Arrays.asList("111","222","333"));
        list2.setNumList(Arrays.asList(1,2,3));
        
        lists.add(list1);
        lists.add(list2);

        log.info("===> before : lists: {}",JSON.toJSONString(lists));

        lists.stream().filter(e->e.getNumList().size() > 0);
        log.info("===> after : lists: {}",JSON.toJSONString(lists));

        List<EmptyList> newList = lists.stream().filter(e->e.getNumList().size() > 0).collect(Collectors.toList());

        log.info("===> finally : lists: {}",JSON.toJSONString(newList));



    }


    @Test
    public void test3(){
        List<EmptyList> lists = new ArrayList<>();
        EmptyList list1 = new EmptyList();
        list1.setName("wrh");
        list1.setAge(20);
        list1.setNumList(new ArrayList<>());

        EmptyList list2 = new EmptyList();
        list2.setName("rjj");
        list2.setList(Arrays.asList("111","222","333"));
        list2.setNumList(Arrays.asList(1,2,3));
        list2.setAge(19);

        lists.add(list1);
        lists.add(list2);

        log.info("===> before : lists: {}",JSON.toJSONString(lists));

        Map<String,Integer> map = new HashMap<>();

        lists.stream().forEach(e->{

            map.put(e.getName(),e.getAge());
            e.setName("superman");
            e.setAge(18);

        });
        log.info("===> after : lists: {}",JSON.toJSONString(lists));
        log.info("===> after : map: {}",JSON.toJSONString(map));
    }
}
