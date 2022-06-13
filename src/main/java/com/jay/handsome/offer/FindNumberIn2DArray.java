package com.jay.handsome.offer;

/**
 * 注释
 *
 * @author jay
 * @date 2022/5/7 10:52
 */
public class FindNumberIn2DArray {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
//        for (int i = 0;i < matrix.length; i++) {
//            for (int j = matrix.length;j >= 0;j--) {
//                if (matrix[i][j] > target) {
//
//                }
//            }
//        }
        if (matrix.length == 0 ) {
            return false;
        }
        int i=0,j=matrix[0].length-1;
        while (i < matrix.length && j >=0) {
            if (matrix[i][j] > target) {
                j--;
            }else if (matrix[i][j] < target) {
                i++;
            }else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int[][] ma = {{1,   4,  7, 11, 15},
//                {2,   5,  8, 12, 19},
//                {3,   6,  9, 16, 22},
//                {10, 13, 14, 17, 24},
//                {18, 21, 23, 26, 30}};
        int[][] ma = {};
        new FindNumberIn2DArray().findNumberIn2DArray(ma,5);
    }
}
