package com.jay.handsome.offer;

/**
 * 剑指 Offer 28. 对称的二叉树
 * https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/
 *
 * @author jay
 * @date 2022/6/14 15:54
 */
public class IsSymmetric {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return help(root.left,root.right);
    }

    public boolean help(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }else if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return help(left.left, right.right) && help(left.right, right.left);
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
