package com.jay.handsome.offer;

/**
 * 注释
 *
 * @author jay
 * @date 2022/5/7 10:22
 */
public class FindRepeatNumber {
    public int findRepeatNumber(int[] nums) {
        for(int i = 0;i < nums.length;i++) {
            for (int j = i+1; j < nums.length;j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    public int findRepeatNumber1(int[] nums) {
        for(int i = 0;i < nums.length;i++) {
            if(nums[i] == i) {
                continue;
            }
            if (nums[nums[i]] == nums[i]) {
                return nums[i];
            }
            int temp = nums[i];
            nums[i] = nums[temp];
            nums[temp] = temp;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {0,2,4,6,1,2,7,7};
        new FindRepeatNumber().findRepeatNumber1(nums);
    }
}
