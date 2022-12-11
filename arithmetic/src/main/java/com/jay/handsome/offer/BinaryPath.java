package com.jay.handsome.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 注释
 *
 * @author jay
 * @date 2022/9/8 15:06
 */
public class BinaryPath {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> listList = new ArrayList<>();
        getPath(root, target, listList, new ArrayList<>());
        return listList;
    }

    public void getPath(TreeNode root, int target, List<List<Integer>> listList, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.val == target && root.left == null && root.right == null) {
            list.add(root.val);
            listList.add(list);
            return;
        }
        list.add(root.val);
        getPath(root.left, target-root.val, listList, new ArrayList<>(list));
        getPath(root.right, target-root.val, listList, new ArrayList<>(list));
    }

    public void getPath1(TreeNode root, int target, List<List<Integer>> listList, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.val == target && root.left == null && root.right == null) {
            listList.add(new ArrayList<>(list));
        }
        getPath(root.left, target-root.val, listList, list);
        getPath(root.right, target-root.val, listList, list);
        list.remove(list.size() -1 );
    }
}
