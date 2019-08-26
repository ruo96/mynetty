package com.wrh.collection.list;

import com.alibaba.fastjson.JSON;
import com.wrh.collection.set.Students;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:07 2019/7/25 0025
 * @Modified By:
 */
public class TestLambdaChange {
    public static void main(String[] args) {
        List<Students> s = new ArrayList<>();
        Students s1 = new Students();
        s1.setName("小明-#");
        s1.setId(0);

        Students s2 = new Students();
        s2.setName("小红-#");
        s2.setId(0);

        s.add(s1);
        s.add(s2);

        System.out.println(JSON.toJSONString(s));

        s.stream().forEach(e->{
            e.setName(e.getName().replace("-",""));
        });

        System.out.println(JSON.toJSONString(s));

        s1.setName(s1.getName().replace("#","-#-#"));
        System.out.println(s1);

        String a1 = "abcd";
        String[] s3 = StringUtils.split(a1,"1");
        System.out.println("===> " + JSON.toJSONString(s3[0]));
        System.out.println("===> " + JSON.toJSONString(s3[1]));
        System.out.println("===> " + JSON.toJSONString(s3[2]));



    }
}
