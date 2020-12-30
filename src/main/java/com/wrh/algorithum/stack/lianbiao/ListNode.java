package com.wrh.algorithum.stack.lianbiao;

/**
 * @author wuruohong
 * @Classname ListNode
 * @Description TODO
 * @Date 2020/12/29 14:19
 */
public class ListNode {
    public int val;
    public int min;//最小值
    public ListNode next;

    public ListNode(int val, int min, ListNode next) {
        this.val = val;
        this.min = min;
        this.next = next;
    }
}
