package com.jay.handsome.offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 剑指 Offer 30. 包含min函数的栈
 * https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/
 *
 * @author jay
 * @date 2022/6/15 14:57
 */
public class MinStack {

    Deque<Integer> stack = new LinkedList<Integer>();
    Deque<Integer> minStack = new LinkedList<Integer>();

    public MinStack() {
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        stack.push(x);
        minStack.push(Math.min(x, minStack.peekFirst()));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peekFirst();
    }

    public int min() {
        return minStack.peekFirst();
    }

}
