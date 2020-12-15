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

    /**
     * 递归
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        // 从下一个节点开始递归
        ListNode reverse = reverseList(head.next);
        head.next.next = head; // 设置下一个节点的 next 为当前节点
        head.next = null; // 把当前节点的 next 赋值为 null，避免循环引用
        return reverse;
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
     * 可以通过循环的方式来实现链表反转，只是这种方法无需重复调用自身方法，只需要一个循环就搞定
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
