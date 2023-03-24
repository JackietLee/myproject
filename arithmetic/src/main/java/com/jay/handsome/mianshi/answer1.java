package com.jay.handsome.mianshi;

import java.util.Arrays;
import java.util.List;

public class answer1 {

    public static void main(String[] args) {
        int[] arr = {6, 4, -3, 5, -2, -1, 0, 1, -6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] < 0 && arr[right] >= 0) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
            if (arr[left] > 0) {
                left++;
            }
            if (arr[right] < 0) {
                right--;
            }
        }
    }
}
