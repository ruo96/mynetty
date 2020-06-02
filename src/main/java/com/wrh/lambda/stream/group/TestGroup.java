package com.wrh.lambda.stream.group;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wuruohong
 * @Classname TestGroup
 * @Description TODO
 * @Date 2020/5/26 14:17
 */
@Slf4j
public class TestGroup {

    class TestClass {
        private Integer groupId;

        private String name;

        private int num;

        public TestClass(Integer groupId, String name, int num) {
            this.groupId = groupId;
            this.name = name;
            this.num = num;
        }

        public Integer getGroupId() {
            return groupId;
        }

        public String getName() {
            return name;
        }

        public int getNum() {
            return num;
        }
    }

    @Test
    public void Test1() {
        TestClass testClass1 = new TestClass(1,"w1",1);
        TestClass testClass2 = new TestClass(2,"w1",1);
        TestClass testClass3 = new TestClass(3,"w1",1);

        List<TestClass> list = new ArrayList<>();
        list.add(testClass1);
        list.add(testClass2);
        list.add(testClass3);

        Map<Integer, List<TestClass>> configMap = list.stream().collect(Collectors.groupingBy(TestClass::getGroupId));
        log.info("{}", JSON.toJSONString(configMap));
    }
}
