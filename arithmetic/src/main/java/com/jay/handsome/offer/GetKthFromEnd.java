package com.jay.handsome.offer;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 * https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/submissions/
 *
 * @author jay
 * @date 2022/6/13 18:01
 */
public class GetKthFromEnd {

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode low = head;
        ListNode fast = head;

        while(fast != null && k>0) {
            fast = fast.next;
            k--;
        }
        while(fast != null) {
            fast = fast.next;
            low = low.next;
        }
        return low;
    }

   public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
   }
}
