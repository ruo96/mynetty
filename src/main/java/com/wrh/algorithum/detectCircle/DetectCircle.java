package com.wrh.algorithum.detectCircle;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wuruohong
 * @Classname DetectCircle
 * @Description TODO
 * @Date 2020/8/21 15:33
 */
@Slf4j
public class DetectCircle {

    /**
     * 给定一个链表， 判断是否有环？如果有环，返回环路的开头节点。
     * 快慢指针的思路，主要的解题思路其实是数学问题。将链表分成两部分，环外链表设有a个节点，环内有b个节点，
     * 设fast指针每次走两步，slow指针每次走一步。则当fast走过2a时slow走过了a，到达了环的入口，这时候fast指针要追上slow指针，
     * 要走过nb-a，而此时slow已经走过了a，fast已经走过了2a。则追上时，fast走过了a+nb，slow走过了nb路程。这是我们将fast指针重新指向链表头head，
     * 并让fast每次也走一步，则fast与slow在这时的第一次相遇时，fast走过了a，slow走过了a+nb刚好在环入口处相遇。此时fast与slow都指向了环入口
     *
     * 作者：5DpwXjfGWd
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/javakuai-man-zhi-zhen-zhao-huan-ru-kou-de-shu-xue-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    @Test
    public void Test15() {
        System.out.println(new ListNode());
        try {
            new ListNode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ListNode detectCycle(ListNode head){
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }

        if (slow != fast) {
            return null;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
