package com.wrh.lambda.stream;

import com.wrh.elasticsearch.Student;
import com.wrh.lambda.stream.vo.BundleInfo;
import com.wrh.lambda.stream.vo.CodePrintInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 上午 11:37 2019/5/25 0025
 * @Modified By:
 */
@Slf4j
public class Testfilter {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("http://123");
        set.add("http://456");
        set.add("http://789");
        set.add("123");

        Set<String> bundleInfoSet = set.stream().filter(b -> b.startsWith("http://"))
                .collect(Collectors.toSet());

        System.out.println(bundleInfoSet);

        Student student = new Student();
        student.setId(1);
        student.setName(null);
        System.out.println(student.getName());

        Map<String,String> map = new HashMap<>();
        map.put("123","456");
        String map1 = map.get("111");
        System.out.println(map1);

        List<String> list = null;
        System.out.println(list.size());
    }

    @Test
    public void test(){
        CodePrintInfo codePrintInfo = new CodePrintInfo();
        codePrintInfo.setBundleInfoSet(new HashSet<>());
        Set<BundleInfo> set = new HashSet<>();

        BundleInfo b1 = new BundleInfo();
        b1.setQrcode("123");

        BundleInfo b2 = new BundleInfo();
        b2.setQrcode("456");

        set.add(b1);
        set.add(b2);

        codePrintInfo.setBundleInfoSet(set);

        String code = codePrintInfo.getBundleInfoSet().stream().findFirst().get().getQrcode();

        log.info("code is : {}",code);
    }
}
