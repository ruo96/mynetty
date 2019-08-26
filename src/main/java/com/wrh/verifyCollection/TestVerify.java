package com.wrh.verifyCollection;

import javax.security.sasl.SaslServer;
import java.util.HashSet;
import java.util.Set;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 12:07 2019/6/24 0024
 * @Modified By:
 */
public class TestVerify {
    public static void main(String[] args) {
        SetInfo setInfo = new SetInfo();
        HashSet<String> names = new HashSet<>();
        names.add("wrh");
        names.add("wrh2");

        setInfo.setName(names);
        setInfo.setHome("");


        System.out.println(setInfo.getName().size());

        Set<String> names1 = new HashSet<>();
        names1.add("wrh1");
        names1.add("wrh2");
        names1.add("wrh3");
        names1.add("wrh4");
         Set<String> sets = new HashSet<>(names1);
         sets.remove("wrh1");
        System.out.println(names1);
        System.out.println(sets);

        setInfo.setName(names1);

        System.out.println("before: " + setInfo);
        setInfo.getName().remove("wrh3");
        System.out.println("after: " + setInfo);
        System.out.println("after: " + names1);

    }
}
