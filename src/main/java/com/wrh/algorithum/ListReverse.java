package com.wrh.algorithum;

/**
 * @author wuruohong
 * @Classname ListReverse
 * @Description TODO
 * @Date 2020/11/17 19:32
 */
public class ListReverse {

    private class ListNode{
        ListNode head;
        ListNode next;
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null){
            return null;
        }
        // 最终排序的倒序链表
        ListNode prev = null;
        while (head != null) {
            // 循环的下个节点
            ListNode next = head.next;
            // 反转节点操作
            head.next = prev;
            // 存储下个节点的上个节点
            prev = head;
            // 移动指针到下一个循环
            head = next;
        }
        return prev;
    }

    /**
     * 时间空间最佳   反转链表
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null) return null;
        // 最终排序的倒序链表
        ListNode prev = null;
        while (head != null) {
            // 循环的下个节点
            ListNode next = head.next;
            // 反转节点操作
            head.next = prev;
            // 存储下个节点的上个节点
            prev = head;
            // 移动指针到下一个循环
            head = next;
        }
        return prev;
    }
}
