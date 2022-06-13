package com.jay.handsome.offer;

/**
 * 剑指 Offer 18. 删除链表的节点
 * https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/
 *
 * @author jay
 * @date 2022/6/13 10:25
 */
public class DeleteNode {
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            head = head.next;
        }
        deleteNode(head.next, head, val);
        return head;
    }

    public void deleteNode(ListNode head, ListNode parent, int val) {
        if (head == null) {
            return;
        }
        if (val == head.val) {
            parent.next = head.next;
        }
        if (null != head.next) {
            deleteNode(head.next, head, val);
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
