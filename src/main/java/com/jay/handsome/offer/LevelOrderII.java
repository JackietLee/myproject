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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
