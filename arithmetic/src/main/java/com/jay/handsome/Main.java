package com.jay.handsome;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String a = in.nextLine();
        char[] charArr = a.toCharArray();
        int flag = 1;//需要乘以几个10
        int sum = 0;
        for(int i = charArr.length-1;i>1;i--) {
            int value = 0;
            switch(charArr[i]){
                case 'A':
                    value = 10;
                    break;
                case 'B':
                    value = 11;
                    break;
                case 'C':
                    value = 12;
                    break;
                case 'D':
                    value = 13;
                    break;
                case 'E':
                    value = 14;
                    break;
                case 'F':
                    value = 15;
                    break;
            }
            sum = sum + value*flag;
            flag = flag*16;
        }
        System.out.println(sum);
    }
}
