package com.jay.handsome.temp;

/**
 * 注释
 *
 * @author jay
 * @date 2022/12/1 15:38
 */
public class Test {
    public static void main(String[] args) {
        int n = 5;
        n |= n >>> 1;
        n |= n >>> 2;

        n |= n >>> 4;

        n |= n >>> 8;

        n |= n >>> 16;
    }
}
