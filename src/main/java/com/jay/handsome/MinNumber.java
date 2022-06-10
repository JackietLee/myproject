package com.jay.handsome;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
 *
 * @author jay
 * @date 2022/6/9 15:32
 */
public class MinNumber {
    public String minNumber(int[] nums) {
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        List<String> collect = Arrays.stream(str).sorted((a, b) -> (a + b).compareTo(b + a)).collect(Collectors.toList());
        collect.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        String s = new MinNumber().minNumber(nums);
        System.out.println(s);
    }
}
