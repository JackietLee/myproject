package com.jay.handsome;

/**
 * https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/
 *
 * @author jay
 * @date 2022/6/7 17:32
 */
public class ArraySearch {

    boolean[][] flag = null;
    public boolean exist(char[][] board, String word) {
        if (null == board || board.length <= 0) return false;
        if (board[0].length <= 0) return false;
        flag = new boolean[board.length][board[0].length];
        char[] chars = word.toCharArray();
        for (int i=0;i<board.length;i++) {
            for (int j=0;j<board[0].length;j++) {
                if (board[i][j] == chars[0]) {
                    boolean b = find(board, chars, 0, i, j);
                    if (b) return true;
                }
            }
        }
        return false;
    }

    public boolean find(char[][] board, char[] chars, int index, int x, int y) {
        if (index > chars.length - 1 || x > board.length-1 || x < 0 || y > board[0].length-1 || y < 0 ) {
            return false;
        }
        if (flag[x][y]) {
            return false;
        }
        if (index == chars.length - 1 && !flag[x][y] && chars[index] == board[x][y] ) {
            return true;
        }
        if (chars[index] == board[x][y]) {
            flag[x][y] = true;
            boolean b = find(board, chars, index + 1, x, y + 1) || find(board, chars, index + 1, x + 1, y) || find(board, chars, index + 1, x - 1, y) || find(board, chars, index + 1, x, y - 1);
            if (!b) flag[x][y] = false;
            return b;
        }else {
            return false;
        }

    }

    public static void main(String[] args) {
//        char[][] chars = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//        char[][] chars = {{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        char[][] chars = {{'a','a','a','a'},{'a','a','a','a'},{'a','a','a','a'}};
//        char[][] chars = {{'a','b'}};
        String s = "aaaaaaaaaaaaa";
        boolean exist = new ArraySearch().exist(chars, s);
        System.out.println(exist);
    }
}
