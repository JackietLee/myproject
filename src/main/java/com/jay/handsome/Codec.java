package com.jay.handsome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof/
 *
 * @author jay
 * @date 2022/6/9 16:00
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> arrayList = new ArrayList<>();
        serialize(arrayList, root);
        return String.join(",",arrayList);
    }

    public void serialize(List<String> list, TreeNode treeNode) {
        if (null == treeNode) {
            list.add("null");
            return;
        }
        list.add(String.valueOf(treeNode.val));
        serialize(list, treeNode.left);
        serialize(list, treeNode.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        List<String> list = new LinkedList<String>(Arrays.asList(split));
        return deserialize(list);
    }

    public TreeNode deserialize(List<String> list) {
        if ("null".equals(list.get(0))) {
            list.remove(0);
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        treeNode.left = deserialize(list);
        treeNode.right = deserialize(list);
        return treeNode;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode deserialize = new Codec().deserialize("1,2,3,null,null,4,5");
        String serialize = new Codec().serialize(deserialize);
        System.out.println(serialize);
    }
}
