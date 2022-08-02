package com.jay.handsome.offer;

import java.util.*;

/**
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
 *
 * @author jay
 * @date 2022/6/21 15:04
 */
public class LevelOrderIII {

    Deque<TreeNode> queue = new LinkedList<TreeNode>();
    List<List<Integer>> list = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root != null) {
            queue.add(root);
        }
        boolean flag = true;
        while (!queue.isEmpty()) {
            Deque<Integer> integers = new LinkedList<>();
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                TreeNode poll = queue.poll();
                if (flag) {
                    integers.addLast(poll.val);
                }else {
                    integers.addFirst(poll.val);
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            list.add(new ArrayList<>(integers));
            flag = !flag;
        }
        return list;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
