package com.wrh.collection.list;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 3:47 2019/7/19 0019
 * @Modified By:
 */
public class TestListStream {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.stream().forEach(e->{
            if(e == 1){
                return;
            }else {
                System.out.println("===>" + e);
            }
        });

        char a = 'a';
        System.out.println(Integer.valueOf(a));

        int b = 5;
        char c = (char) b;
        System.out.println(c);

        int d = 10;
        char e = (char)((d/10) +97);
        String f = String.valueOf(e);
        System.out.println(f);

        String g = "编号:4603-名字:佛珠-大-小--名字:123";
        System.out.println("===> index: "+g.indexOf("-名字:"));
        System.out.println("===> name: " + g.substring(g.indexOf("-名字:") + 4));
        String[] gs = StringUtils.split(g,"名字");
        for (int i = 0; i < gs.length; i++) {
            System.out.println(gs[i]);
        }
    }
}
