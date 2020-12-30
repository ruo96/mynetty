package com.wrh.algorithum.lianbiao.fanzhuan;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wuruohong
 * @Classname ReverseList
 * @Description TODO
 * @Date 2020/7/2 11:29
 */
@Slf4j
public class ReverseList {
    public class ListNode {
      int val;
      ListNode next;
     ListNode(int x) { val = x; }
  }

//    public ListNode

    /**
     * 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}
