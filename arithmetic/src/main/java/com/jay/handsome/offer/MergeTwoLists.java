package com.jay.handsome.offer;

/**
 * 剑指 Offer 25. 合并两个排序的链表
 * https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
 *
 * @author jay
 * @date 2022/6/14 11:52
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode listNode = null;
        ListNode current = null;
        while (l1 != null || l2 != null) {
            if ((l1!=null && l2 != null && l1.val < l2.val) || (l1!=null && l2 == null)) {
                if (listNode == null) {
                    listNode = l1;
                    current = listNode;
                }else {
                    current.next = l1;
                    current = current.next;
                }
                l1 = l1.next;
            }else {
                if (listNode == null) {
                    listNode = l2;
                    current = listNode;
                }else {
                    current.next = l2;
                    current = current.next;
                }
                l2 = l2.next;
            }
        }
        return listNode;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(4);
        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(3);
        listNode2.next.next = new ListNode(4);
        new MergeTwoLists().mergeTwoLists(listNode, listNode2);
    }
}
