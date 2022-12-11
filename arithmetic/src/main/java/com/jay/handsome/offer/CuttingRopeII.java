package com.jay.handsome.offer;

/**
 * https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/
 *
 * @author jay
 * @date 2022/6/8 15:26
 */
public class CuttingRopeII {
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
        if (n == 4) return 4;
        long result = 1;
        while(n > 4) {
            result *= 3;
            result %= 1000000007;
            n -= 3;
        }
        return (int)(n * result % 1000000007);
    }

    public int cuttingRope1(int n) {
        if (n < 2) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;
        int a = n / 3;
        int b = n % 3;
        if (b==0) return (int) myPow(3,a);
        if (b==1) return (int) myPow(3,a-1)*4;
        return (int) myPow(3,a)*2;
    }

    public long myPow(long base, int num){
        long res = 1;
        while(num > 0){
            if((num & 1) == 1){
                res *= base;
                res %= 1000000007;
            }
            base *= base;
            base %= 1000000007;
            num >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new CuttingRopeII().cuttingRope(10));
    }
}
