package com.wrh.collection.set.testRemove;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 4:50 2019/8/1 0001
 * @Modified By:
 */
public class TestCodeConnectionRemove {
    public static void main(String[] args) {
        CodeConnection connection = new CodeConnection();

        Set<CodeConnectionDetail> codes = new HashSet<>();
        CodeConnectionDetail s1 = new CodeConnectionDetail();
        s1.setQrcode("1-2-3");
        codes.add(s1);
        CodeConnectionDetail s2 = new CodeConnectionDetail();
        s2.setQrcode("4-5-6");
        codes.add(s2);
        CodeConnectionDetail s3 = new CodeConnectionDetail();
        s3.setQrcode("7-8-9");
        codes.add(s3);
        CodeConnectionDetail s4 = new CodeConnectionDetail();
        s4.setQrcode("10-11-12");
        codes.add(s4);

        connection.setDetails(codes);

        System.out.println("===> before connection: " + JSON.toJSONString(connection));

        /*childCodeDetail.removeAll(childCodeDetail.stream()
                .filter(i -> codeSet.contains(i.getQrCode()))
                .collect(Collectors.toSet()));
        childCodes.removeAll(codeSet);*/

        Set<String> set = Sets.newHashSet("12345");
        System.out.println("===> " + JSON.toJSONString(set));

        HashSet<String> codeSet = new HashSet<>();
        codeSet.add("1-2-3");
        connection.getDetails().removeAll(connection.getDetails().stream().filter(i -> codeSet.contains(i.getQrcode()) ).collect(Collectors.toList()));

        System.out.println("===> after connection: " + JSON.toJSONString(connection));
    }
}
