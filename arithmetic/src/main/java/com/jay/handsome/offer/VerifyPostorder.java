package com.jay.handsome.offer;

import java.util.Date;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 *
 * @author jay
 * @date 2022/7/5 14:21
 */
public class VerifyPostorder {
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length-1);
    }

    private boolean recur(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }
        int p = i;
        while (postorder[p] < postorder[j]) p++;
        int m = p;
        while (postorder[p] > postorder[j]) p++;
        return p==j && recur(postorder, i, m-1) && recur(postorder, m, j-1);
    }

    public static void main(String[] args) {
//        int i = -(31 ^ 4) * 104;
//        int i1 = i | 0;
//        int i2 = i & 1;
//        for (int j = 1;j<=32 ; j++){
//        i = i >> 1;
//        System.out.println(i);
//        }
//        double v = Math.pow(31, 4) * 104;
        Date date = new Date(1659421318028l);
        System.out.println(date);
    }

}
