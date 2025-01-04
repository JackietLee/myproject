package com.jay.handsome.stringSum;

public class StringSum {
    public static void main(String[] args) {
        String s = "12345";
        String s1 = "100000000000000000000000000000000";
        String s2 = "10034343434344334000000000000000000000000000000000";
        System.out.println(sum(s,s1,s2));
    }

    public static String sum(String s1, String s2, String s3) {
        int a = s1.length()>s2.length()?s1.length():(s2.length()>s3.length()?s2.length():s3.length());
        StringBuilder sb1 = new StringBuilder();
        sb1.append("0".repeat((a - s1.length())));
        sb1.append(s1);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("0".repeat((a - s2.length())));
        sb2.append(s2);
        StringBuilder sb3 = new StringBuilder();
        sb3.append("0".repeat((a - s3.length())));
        sb3.append(s3);
        int d = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = a-1; i >= 0; i--) {
            int i1 = Integer.parseInt(String.valueOf(sb1.charAt(i))) + Integer.parseInt(String.valueOf(sb2.charAt(i))) + Integer.parseInt(String.valueOf(sb3.charAt(i)));
            d = i1/10;
            sb.append(i1%10);
        }
        if (d>0) {
            sb.append(d);
        }
        sb.reverse();
        return sb.toString();
    }
}
