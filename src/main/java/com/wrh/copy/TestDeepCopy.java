package com.wrh.copy;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.wrh.copy.utils.DeepCopyUtils;
import com.wrh.copy.vo.NodeProperty;
import com.wrh.copy.vo.StageNode;
import lombok.extern.slf4j.Slf4j;


import org.apache.http.client.utils.CloneUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 6:37 2019/10/10 0010
 * @Modified By:
 */
@Slf4j
public class TestDeepCopy {

    @Test
    public void test(){
        DeepCopyVo vo = new DeepCopyVo();

        vo.setName("wrh");
        vo.setAge(18);
        List<String> cars = Lists.newArrayList("benz","bwm");
        vo.setCars(cars);




        log.info("========================================");
        DeepCopyVo vo2 = new DeepCopyVo();
        BeanUtils.copyProperties(vo,vo2);
        vo.setAge(14);
        vo.getCars().add("dongfeng");
        log.info("=== vo is :{}", JSON.toJSONString(vo));
        log.info("=== vo2 is :{}", JSON.toJSONString(vo2));

    }

    @Test
    public void test1()  {
        DeepCopyVo vo = new DeepCopyVo();

        vo.setName("wrh");
        vo.setAge(18);
        List<String> cars = Lists.newArrayList("benz","bwm");
        vo.setCars(cars);

        log.info("========================================");
        DeepCopyVo vo2 = new DeepCopyVo();
        BeanUtils.copyProperties(vo,vo2);
        vo2.setCars(DeepCopyUtils.cloneList(vo.getCars()));
        vo.setAge(14);
        vo.getCars().add("dongfeng");
        log.info("=== vo is :{}", JSON.toJSONString(vo));
        log.info("=== vo2 is :{}", JSON.toJSONString(vo2));
    }

    @Test
    public void test3()  {
        DeepCopyVo vo = new DeepCopyVo();

        vo.setName("wrh");
        vo.setAge(18);
        List<String> cars = Lists.newArrayList("benz","bwm");
        vo.setCars(cars);

        HashSet<String> thingSet = new HashSet<>();
        thingSet.add("111");
        thingSet.add("222");
        thingSet.add("333");

        vo.setThing(thingSet);

        DeepCopyVo vo1 = new DeepCopyVo();
        BeanUtils.copyProperties(vo,vo1);
        vo1.setThing(new HashSet<>(vo.getThing()));
        log.info("=== vo is :{}", JSON.toJSONString(vo));
        log.info("=== vo1 is :{}", JSON.toJSONString(vo1));

        vo.getThing().add("444");

        log.info("========================================");
        log.info("=== vo is :{}", JSON.toJSONString(vo));
        log.info("=== vo1 is :{}", JSON.toJSONString(vo1));
    }

    @Test
    public void test4()  {

        HashSet<DeepCopyVo> vos = new HashSet<>();

        DeepCopyVo vo = new DeepCopyVo();

        vo.setName("wrh");
        vo.setAge(18);
        List<String> cars = Lists.newArrayList("benz","bwm");
        vo.setCars(cars);

        HashSet<String> thingSet = new HashSet<>();
        thingSet.add("111");
        thingSet.add("222");
        thingSet.add("333");

        vo.setThing(thingSet);

        vos.add(vo);

        HashSet<DeepCopyVo> vos1 = new HashSet<>(vos);
        log.info("=== vos is :{}",JSON.toJSONString(vos));
        log.info("=== vos1 is :{}",JSON.toJSONString(vos1));


        vos.iterator().next().getThing().add("444");

        log.info("========================================");
        log.info("=== vos is :{}",JSON.toJSONString(vos));
        log.info("=== vos1 is :{}",JSON.toJSONString(vos1));
    }

    @Test
    public void test2()  {
        StageNode s1 = new StageNode();
        s1.setStageId(0);
        s1.setParentStageId(0);
        s1.setSortCode(0);
        s1.setStageName("节点1");
        s1.setThirdPartyFlag(0);

        List<NodeProperty> properties = new ArrayList<>();
        NodeProperty p1 = new NodeProperty();
        p1.setPropertyName("111");
        p1.setDefaultValue("111");
        p1.setDataType(0);
        p1.setPropertyId(0);
        p1.setDefaultType("111");
        p1.setStageId(0);
        p1.setSortCode(0);
        p1.setAllowAdd(0);
        p1.setAllowApp(0);
        p1.setPictureSkipUrl("111");
        p1.setLinkValue("111");

        properties.add(p1);
        s1.setProperties(properties);


        StageNode s2 = new StageNode();
        BeanUtils.copyProperties(s1,s2);

        s2.setProperties(DeepCopyUtils.cloneList(s1.getProperties()));
        log.info("=== s1 is :{}",JSON.toJSONString(s1));
        log.info("=== s2 is :{}",JSON.toJSONString(s2));


        NodeProperty p2 = new NodeProperty();
        p2.setPropertyName("222");
        p2.setDefaultValue("222");
        p2.setDataType(0);
        p2.setPropertyId(0);
        p2.setDefaultType("222");
        p2.setStageId(0);
        p2.setSortCode(0);
        p2.setAllowAdd(0);
        p2.setAllowApp(0);
        p2.setPictureSkipUrl("222");
        p2.setLinkValue("222");
        s1.getProperties().add(p2);



        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("=== s1 is :{}",JSON.toJSONString(s1));
        log.info("=== s2 is :{}",JSON.toJSONString(s2));
    }

    @Test
    public void test5()  {

        /**
         * 直接的list无法使用beanUTILS
         */
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        List<String> list1 = new ArrayList<>();
        BeanUtils.copyProperties(list,list1);

        log.info("=== s1 is :{}",JSON.toJSONString(list));
        log.info("=== list1 is :{}",JSON.toJSONString(list1));
       list.add("5");
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("=== s1 is :{}",JSON.toJSONString(list));
        log.info("=== list1 is :{}",JSON.toJSONString(list1));
    }

    @Test
    public void test6()  {


        /**
         * beanUtils 复制的时候，对于基本值类型是深拷贝，也就是值复制，但是对于复杂对象，就是引用复制，指向是同一个
         * String, int, Integer, boolean, Boolean,char, Character
         */
        DeepCopyVo vo = new DeepCopyVo();
        vo.setName("w1");
        vo.setAge(18);
        vo.setMoney(100);
        vo.setFirstChar('A');
        vo.setFirstCharacter('A');
        vo.setCars(Lists.newArrayList("benz","bwm"));
        vo.setHide(true);
        vo.setShow(true);

        DeepCopyVo vo1 = new DeepCopyVo();

        BeanUtils.copyProperties(vo,vo1);
//        vo1 = DeepCopyUtils.clone(vo);

        log.info("=== vo is :{}",JSON.toJSONString(vo));
        log.info("=== vo1 is :{}",JSON.toJSONString(vo1));

        vo.setName("w11");
        vo.setAge(19);
        vo.setMoney(200);
        vo.setFirstChar('B');
        vo.setFirstCharacter('B');
        vo.getCars().add("dazhong");
        vo.setHide(false);
        vo.setShow(false);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("=== vo is :{}",JSON.toJSONString(vo));
        log.info("=== vo1 is :{}",JSON.toJSONString(vo1));
    }
}
