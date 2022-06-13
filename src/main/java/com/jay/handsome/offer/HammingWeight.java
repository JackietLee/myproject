package com.jay.handsome.offer;

/**
 * https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 *
 * @author jay
 * @date 2022/6/10 17:14
 */
public class HammingWeight {

    /**
     * java是有符号的，所以int只能左移31位
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int count = 0;
        boolean flag = true;
        if(n<0) {
            flag = false;
        }
        for(int i = 0;i<31;i++) {
            if((n & 1)==1) {
                count += 1;
            }
            n >>= 1;
        }
        if (flag) {
            return count;
        }else {
            return count+1;
        }
    }
}
