package com.jay.handsome.offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 剑指 Offer 31. 栈的压入、弹出序列
 * https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
 *
 * @author jay
 * @date 2022/6/15 15:22
 */
public class ValidateStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new LinkedList<>();
        int i = 0;
        for(int num : pushed) {
            stack.push(num); // num 入栈
            while(!stack.isEmpty() && stack.peek() == popped[i]) { // 循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

}
