package com.jay.handsome.sortMethod;

public class BubbleSort {

    public void bubbleSort(int[] arr) {
        if (arr == null) {
            return;
        }
        if (arr.length < 2) {
            return;
        }
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

}
