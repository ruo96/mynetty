package com.wrh.list.linkedlist;

import java.util.LinkedList;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:41 2019/2/1 0001
 * @Modified By:
 */
public class TestLinkedList {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        System.out.println(linkedList);
        linkedList.addFirst("4");
        System.out.println(linkedList);
        linkedList.addLast("5");
        System.out.println(linkedList);
    }
}
