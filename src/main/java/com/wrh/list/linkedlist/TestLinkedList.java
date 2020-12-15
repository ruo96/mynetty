package com.wrh.list.linkedlist;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:41 2019/2/1 0001
 * @Modified By:
 */
@Slf4j
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

    /**
     * add 会报错， offer会返回false， peek只是观察
     */
    @Test
    public void Test28() {
        LinkedList list = new LinkedList();
        list.add("java");
        list.add("linux");
        list.add("python");
        list.addFirst("i study");
        list.addLast("very much");
        System.out.println(list);
        System.out.println(list.offerFirst("at first"));
        System.out.println(list);

        System.out.println("================iterator=================");
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }

    /**
     * 链表实现栈 先进先出
     */
    @Test
    public void Test53() {
        LinkedList list = getLinkedList();
        System.out.println(list);
        while (!list.isEmpty()) {
            System.out.println(list.poll());
        }
    }

    private LinkedList getLinkedList() {
        LinkedList list = new LinkedList();
        list.add("java");
        list.add("linux");
        list.add("python");
        return list;
    }

    LinkedList list = getLinkedList();

    /**
     * 链表实现后进先出的队列
     */
    @Test
    public void Test73() {
        LinkedList list = getLinkedList();
        System.out.println(list);
        while (!list.isEmpty()) {
            System.out.println(list.pollLast());
        }
    }
}
