package com.jay.handsome.huawei;

import java.util.ArrayList;
import java.util.List;

public class TreeNod {

      public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
      }

    public class Solution {
        /**
         *
         * @param root TreeNode类 the root of binary tree
         * @return int整型二维数组
         */
        public int[][] threeOrders (TreeNode root) {
            if(root == null) {
                return new int[3][0];
            }
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            List<Integer> list3 = new ArrayList<>();
            // write code here
            pre(root, list1);
            mid(root, list2);
            post(root, list3);
            int[][] arr = new int[3][list1.size()];
            for(int i = 0; i < list1.size(); i++){
                arr[0][i] = list1.get(i);
                arr[1][i] = list2.get(i);
                arr[2][i] = list3.get(i);

            }
            return arr;
        }

        public void pre(TreeNode root, List<Integer> list) {
            if(root == null) {
                return;
            }
            list.add(root.val);
            pre(root.left, list);
            pre(root.right, list);
        }

        public void mid(TreeNode root, List<Integer> list) {
            if(root == null) {
                return;
            }
            mid(root.left, list);
            list.add(root.val);

            mid(root.right, list);
        }

        public void post(TreeNode root, List<Integer> list) {
            if(root == null) {
                return;
            }

            post(root.left, list);
            post(root.right, list);
            list.add(root.val);
        }
    }

    public static void main(String[] args) {
        Solution solution = new TreeNod().new Solution();
        TreeNode treeNode = new TreeNod().new TreeNode();
        treeNode.val = 1;
        TreeNode treeNodeLeft = new TreeNod().new TreeNode();
        TreeNode treeNodeRight = new TreeNod().new TreeNode();
        treeNode.left =treeNodeLeft;
        treeNodeLeft.val = 2;
        treeNode.right =treeNodeRight;
        treeNodeRight.val = 3;

        int[][] ints = solution.threeOrders(treeNode);
        System.out.println(ints);
    }
}
