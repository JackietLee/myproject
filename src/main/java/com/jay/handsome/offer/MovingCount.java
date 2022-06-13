package com.jay.handsome.offer;

/**
 * https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
 *
 * @author jay
 * @date 2022/6/8 10:25
 */
public class MovingCount {
    boolean[][] flag = null;
    int count = 0;
    public int movingCount(int m, int n, int k) {
        flag = new boolean[m][n];
        find(m, n, k, 0, 0);
        return count;
    }

    public void find(int m, int n, int k,  int x, int y) {
        if (x > m-1 || x < 0 || y > n-1 || y < 0  ) {
            return;
        }
        if (((x)%10 + ((x)/10)%10 + ((x)/100)%10 + (y)%10 + ((y)/10)%10 + ((y)/100)%10) > k) {
            return;
        }
        if (!flag[x][y]) {
            count++;
            flag[x][y] = true;
            find(m, n, k , x, y + 1);
            find(m, n, k , x + 1, y);
        }

    }

    private int bitSum(int x){
        int sum = 0;
        while(x != 0){
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int i = new MovingCount().movingCount(11, 8, 16);
        System.out.println(i);
    }
}
