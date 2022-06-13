package com.jay.handsome.offer;

/**
 * 注释
 *
 * @author jay
 * @date 2022/6/9 16:43
 */
public class Fib {

    public int fib(int n){
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int first = 0;
        int second = 1;
        int temp = 0;
        for (int i = 2; i<=n;i++) {
            temp = (first+second)%1000000007;
            first = second;
            second = temp;
        }
        return temp;
    }

    public static void main(String[] args) {
        int fib = new Fib().fib(45);
        System.out.println(fib);
    }
}
