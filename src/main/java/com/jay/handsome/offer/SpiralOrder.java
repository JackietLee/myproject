package com.jay.handsome.offer;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 * https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 *
 * @author jay
 * @date 2022/6/14 16:07
 */
public class SpiralOrder {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||matrix[0].length == 0) return new int[0];
        int count = matrix.length*matrix[0].length;
        int[] array = new int[count];
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int direction = 0;
        int row = 0;
        int column = 0;
        for (int i = 0; i < count; i++) {
            array[i] = matrix[row][column];
            visited[row][column] = true;
            int nextRow = getRow(row,direction);
            int nextColumn = getColumn(column,direction);
            if (nextRow < 0 || nextRow >= matrix.length || nextColumn < 0 || nextColumn >= matrix[0].length || visited[nextRow][nextColumn]) {
                direction = direction + 1;
            }
            row = getRow(row,direction);
            column = getColumn(column,direction);
        }
        return array;
    }

    public int getRow(int row, int direction) {
        if (direction%4 == 0) {
            return row;
        }else if (direction%4 == 1) {
            return row+1;
        }else if (direction%4 == 2) {
            return row;
        }else {
            return row-1;
        }
    }

    public int getColumn(int column, int direction) {
        if (direction%4 == 0) {
            return column+1;
        }else if (direction%4 == 1) {
            return column;
        }else if (direction%4 == 2) {
            return column-1;
        }else {
            return column;
        }
    }

    public static void main(String[] args) {
        int[][] array = {{1,2,3},{4,5,6},{7,8,9}};
        new SpiralOrder().spiralOrder(array);
    }
}
