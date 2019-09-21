package com.wrh.beancopy;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

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
}
