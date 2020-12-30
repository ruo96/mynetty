package com.wrh.beancopy;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.converter.TypeConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 1:37 2019/8/31 0031
 * @Modified By:
 */
@Slf4j
public class TestBeanCopy {

    @Test
    public  void test(){

        List<GroupInfo> groupInfos = new ArrayList<>();

        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroupId(10);
        groupInfo.setName("采集组1");

        GroupInfo groupInfo1 = new GroupInfo();
        groupInfo1.setGroupId(11);
        groupInfo1.setName("采集组2");

        groupInfos.add(groupInfo1);


        PdaUser user = new PdaUser();
        user.setId(1);
        user.setUserName("小明");
        user.setGroupInfoList(groupInfos);

        log.info("新造的对象：{}", JSON.toJSONString(user));

        PdaUser user1 = new PdaUser();
        BeanUtils.copyProperties(user, user1);

        log.info("复制的对象：{}", JSON.toJSONString(user1));

        user.setGroupInfoList(new ArrayList<>());
        log.info("变动后的对象：{}", JSON.toJSONString(user));
        log.info("变动后的对象：{}", JSON.toJSONString(user1));

    }

    @Test
    public  void test1(){


        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroupId(0);
        groupInfo.setName("组1");
        groupInfo.setIntegerValue(1);
        groupInfo.setStrValue("wrh");
        groupInfo.setBoolValue(true);
        groupInfo.setLongValue(10L);




        log.info("新造的对象：{}", JSON.toJSONString(groupInfo));

        GroupInfo groupInfo1 = new GroupInfo();
        BeanUtils.copyProperties(groupInfo, groupInfo1);

        log.info("复制的对象：{}", JSON.toJSONString(groupInfo1));

        groupInfo.setBoolValue(false);
        groupInfo.setLongValue(200L);
        groupInfo.setStrValue("rjj");


        log.info("变动后的对象：{}", JSON.toJSONString(groupInfo));
        log.info("变动后的对象：{}", JSON.toJSONString(groupInfo1));

    }

    private GroupInfo getSource() {
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroupId(0);
        groupInfo.setName("组1");
        groupInfo.setIntegerValue(1);
        groupInfo.setStrValue("wrh");
        groupInfo.setBoolValue(true);
        groupInfo.setLongValue(10L);

        return  groupInfo;
    }

    @Test
    public void Test87() {
        BeanCopier bc = BeanCopier.create(GroupInfo.class, GroupInfo.class,false);
        GroupInfo source = getSource();
        GroupInfo target = new GroupInfo();
        bc.copy(source, target, null);
        System.out.println(source);
        System.out.println(target);
        System.out.println(source == target);

    }

    @Test
    public void Test112() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(GroupInfo.class, GroupInfo.class);
        MapperFacade mapper = mapperFactory.getMapperFacade();
        GroupInfo source = getSource();
        GroupInfo target = mapper.map(source, GroupInfo.class);
        System.out.println(source);
        System.out.println(target);
        System.out.println(source == target);

    }
}
