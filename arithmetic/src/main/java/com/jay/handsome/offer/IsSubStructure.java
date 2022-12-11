package com.jay.handsome.offer;

/**
 * 剑指 Offer 26. 树的子结构
 * https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/
 *
 * @author jay
 * @date 2022/6/14 14:36
 */
public class IsSubStructure {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (null == B || null == A) {
            return false;
        }
        if (is(A,B) ) {
            return true;
        }
        return isSubStructure(A.left,B) || isSubStructure(A.right,B);
    }

    public boolean is(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        return is(A.right,B.right) && is(A.left,B.left);
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
