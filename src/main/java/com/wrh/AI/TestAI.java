package com.wrh.AI;

import java.util.Scanner;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:29 2018/12/14 0014
 * @Modified By:
 */
public class TestAI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str;

        while (true){
            str = sc.next();
            str = str.replace("吗","");
            str = str.replace("?","!");
            str = str.replace("?","!");
            str = str.replace("我","你");
            System.out.println(str);
        }
    }
}
