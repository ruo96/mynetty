package com.wrh.algorithum.detectCircle;

/**
 * @author wuruohong
 * @Classname ListNode
 * @Description TODO
 * @Date 2020/8/21 15:35
 */
public class Node<E> {

    E item;
    Node<E> next;
    Node<E> prev;

    public Node(E item, Node<E> next, Node<E> prev) {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }
}
