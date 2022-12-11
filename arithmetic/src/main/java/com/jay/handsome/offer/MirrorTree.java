package com.jay.handsome.offer;

/**
 * 剑指 Offer 27. 二叉树的镜像
 * https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/
 *
 * @author jay
 * @date 2022/6/14 15:23
 */
public class MirrorTree {

    public TreeNode mirrorTree(TreeNode root) {
        help(root);
        return root;
    }

    public void help(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        help(root.left);
        help(root.right);
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
