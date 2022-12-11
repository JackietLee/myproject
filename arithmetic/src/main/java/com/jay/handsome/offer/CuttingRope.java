package com.jay.handsome.offer;

/**
 * https://leetcode.cn/problems/jian-sheng-zi-lcof/
 *
 * @author jay
 * @date 2022/6/8 15:26
 */
public class CuttingRope {
    /**
     * 当绳子长度为2时，只可能剪成长度为1的两段
     * 当绳子长度为3时，可能把绳子剪成长度分别为1和2的两段或者长度都为1的三段，因为1 * 2>1 * 1 * 1
     * 当绳子长度>=5时，尽可能多的剪长度为3的绳子，当剩下的绳子长度为1时，把绳子剪成长度为4的绳子
     *
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if (n < 2) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;
        int a = n / 3;
        int b = n % 3;
        if (b==0) return (int) Math.pow(3,a);
        if (b==1) return (int) Math.pow(3,a-1)*4;
        return (int) Math.pow(3,a)*2;
    }

    public int cuttingRope1(int n) {
        if (n <= 3) return n-1;
        int[] array = new int[n+1];
        array[1] = 0;
        array[2] = 1;
        array[3] = 2;
        for (int i = 4; i<=n;i++) {
            for (int j = 1;j<i-1;j++) {
                array[i] = Math.max(array[i],Math.max((i-j)*j, array[j]*(i-j)));
//                array[i] = Math.max(array[i],array[j]*(i-j));
            }
        }
        return array[n];
    }

    public static void main(String[] args) {
        System.out.println(new CuttingRope().cuttingRope1(10));
    }
}
