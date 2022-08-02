package com.jay.handsome.offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer 32 - II. 从上到下打印二叉树
 * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
 *
 * @author jay
 * @date 2022/6/15 15:38
 */
public class LevelOrderII {

    Queue<Body> queue = new LinkedList<Body>();
    Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
    List<List<Integer>> list = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root != null) {
            Body body = new Body();
            body.val = 0;
            body.node = root;
            queue.add(body);
        }
        while (!queue.isEmpty()) {
            Body poll = queue.poll();
            int val = poll.val;
            if (list.size()<val+1) {
                list.add(new ArrayList<>());
            }
            List<Integer> integers = list.get(val);
            integers.add(poll.node.val);
            if (poll.node.left != null) {
                Body body = new Body();
                body.val = val+1;
                body.node = poll.node.left;
                queue.add(body);
            }
            if (poll.node.right != null) {
                Body body = new Body();
                body.val = val+1;
                body.node = poll.node.right;
                queue.add(body);
            }
        }
        return list;
    }

    public class Body {
        int val;
        TreeNode node;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root != null) {
            queue1.add(root);
        }
        while (!queue1.isEmpty()) {
            List<Integer> integers = new ArrayList<>();
            list.add(integers);
            int length = queue1.size();
            for (int i = 0; i < length; i++) {
                TreeNode poll = queue1.poll();
                integers.add(poll.val);
                if (poll.left != null) {
                    queue1.add(poll.left);
                }
                if (poll.right != null) {
                    queue1.add(poll.right);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(9);
        TreeNode treeNode2 = new TreeNode(20);
        TreeNode treeNode3 = new TreeNode(15);
        TreeNode treeNode4 = new TreeNode(7);

        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;
        new LevelOrderII().levelOrder2(treeNode);
    }
}
