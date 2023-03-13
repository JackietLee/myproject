package com.jay.handsome.sortMethod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class QuickSort {

    public void quickSort(int[] arr) {
        quickSortInternal(arr, 0, arr.length-1);
    }

    private void quickSortInternal(int[] arr, int l, int r) {
        if (l>=r) {
            return;
        }
        int v = arr[l];
        int j = l;
        for (int i = l+1; i <= r ; i++) {
            if (arr[i] < v) {
                int temp = arr[i];
                arr[i] = arr[++j];
                arr[++j] = temp;
            }
        }
        int temp = arr[l];
        arr[l] = arr[j];
        arr[j] = temp;
        quickSortInternal(arr, l, j);
        quickSortInternal(arr, j+1, r);
    }

    public static void main(String[] args) {
        int i = 1;
        int j = i++;
        System.out.println(j);
        i = 1;
        j = ++i;
        System.out.println(j);
        i = 1;
        test(i++);
        i = 1;
        test(++i);
    }

    public static void test(int i) {
        HashSet s = new HashSet<Integer>();
        int[] arr = new int[6];
                System.out.println(i);
        switch (arr[i]){
            case 1:
        }
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        for (Map.Entry<Object, Object> objectObjectEntry : objectObjectHashMap.entrySet()) {

        }
    }

}
