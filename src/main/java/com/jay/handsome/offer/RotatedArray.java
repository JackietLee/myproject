package com.jay.handsome.offer;

/**
 * https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 *
 * @author jay
 * @date 2022/6/7 16:30
 */
public class RotatedArray {

    public static int minArray(int[] numbers) {
        int length = numbers.length;
        int low = 0;
        int high = length-1;
        int center = low + (high-low)/2;
        while (high > low) {
            if (numbers[center] < numbers[high]) {
                high = center;
            }else if (numbers[center] > numbers[high]) {
                low = center+1;
            }else {
                high = high-1;
            }
            center = low + (high-low)/2;
        }
        return numbers[center];
    }

    public static void main(String[] args) {
        int[] array = {3,4,5,1,2};
        System.out.println(minArray(array));
    }
}
