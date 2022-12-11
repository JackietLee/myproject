package com.jay.handsome.offer;

/**
 * 剑指 Offer 24. 反转链表
 * https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/
 *
 * @author jay
 * @date 2022/6/14 9:11
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
