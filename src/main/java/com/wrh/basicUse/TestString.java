package com.wrh.basicUse;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.wrh.basicUse.vo.OnLineCleanParamDto;
import com.wrh.functionInterfaceTest.Student;
import com.wrh.utils.Md5Utils;
import com.wrh.utils.ObjectCheckHandleUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * @Classname TestString
 * @Description TODO
 * @Date 2020/1/9 16:59
 * @Created by wuruohong
 */
@Slf4j
public class TestString {
    @Test
    public void Test() {
        String uid = "123";
        String inter = null;
        String ds = "456";

        String uidRowKey = Joiner.on("_").skipNulls().join(uid, inter,ds);
        log.info(" result: {}",uidRowKey);
    }

    /**
     * 测试md5工具类
     */
    @Test
    public void Test1() {
        String a = "123";
        String md5Hash = Md5Utils.encrypt(a);
        log.info(" result: {}",md5Hash);

    }

    @Test
    public void Test2() {
        OnLineCleanParamDto student = new OnLineCleanParamDto();
        student.setTourMark("123   ");
        student.setUid("234  ");
        student.setBuvid("21312  ");
        student.setGameId(" 123123s ");
        student.setServerRecTime("dsfs ");
        student.setIntervalTime("  adfas");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        /*for (int i = 0; i < 10000; i++) {

            boolean flag = ObjectCheckHandleUtils.handle(student);
        }*/
        boolean flag = ObjectCheckHandleUtils.handle(student);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
        System.out.println(flag);
        System.out.println(JSON.toJSONString(student));
    }
}
