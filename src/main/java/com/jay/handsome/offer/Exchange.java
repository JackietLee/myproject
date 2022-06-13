package com.jay.handsome.offer;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 *
 * @author jay
 * @date 2022/6/13 16:46
 */
public class Exchange {

    public int[] exchange(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        int i = 0;
        int j = nums.length-1;
        while(i<j) {
            while (i<j && isOdd(nums[i])) i++;
            while (i<j && !isOdd(nums[j])) j--;
            if (i<j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
            i++;
            j--;
        }
        return nums;
    }

    public boolean isOdd(int number) {
        if ((number & 1) == 1) {
            return true;
        }
        return false;
    }

    public int[] exchange1(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        int i = 0;
        int j = nums.length-1;
        while(j>i) {
            if (isOdd(nums[i])) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }else {
                i++;
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        int[] exchange = new Exchange().exchange1(a);
    }
}
