package com.jay.handsome.normal;

public class TwoSum {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode();
        addTwoNumbers(l1, l2, listNode, false);
        return listNode;
    }

    private static void addTwoNumbers(ListNode l1, ListNode l2, ListNode listNode, boolean b) {
        boolean flag = false;
        int i = (l1==null?0:l1.val) + (l2==null?0:l2.val);
        if (b) {
            i = i+1;
        }
        if (i>=10) {
            i -=10;
            flag=true;
        }
        listNode.val=i;
        if (!flag && (l1==null || l1.next==null)&&(l2==null || l2.next==null)) {
            return;
        }
        listNode.next=new ListNode();
        addTwoNumbers(l1==null?null:l1.next, l2==null?null:l2.next, listNode.next, flag);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        addTwoNumbers(l1,l2);
    }
}
