package com.jay.handsome.mianshi;

import java.util.Scanner;

public class LoopStr {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        char[] chars = a.toCharArray();
        int i = 0;
        boolean flag = true;
        while (i < (chars.length / 2)) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                flag = false;
                break;
            }

        }
        System.out.println(flag);
    }
}
