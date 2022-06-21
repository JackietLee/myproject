package com.jay.handsome.offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
 *
 * @author jay
 * @date 2022/6/15 15:38
 */
public class LevelOrder {

    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    List<Integer> list = new ArrayList<Integer>();

    public int[] levelOrder(TreeNode root) {

        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            list.add(poll.val);
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
        int[] ints = new int[list.size()];
        int i = 0;
        for (Integer integer : list) {
            ints[i] = integer;
            i++;
        }
        return ints;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
