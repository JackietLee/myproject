package com.jay.handsome;

/**
 * 注释
 *
 * @author jay
 * @date 2022/5/7 11:35
 */
public class ReplaceSpace {

    public String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (char aChar : chars) {
            if (aChar == ' ') {
                count++;
            }
        }
        int oldIndex = chars.length-1;
        int newIndex = chars.length+2*count-1;
        char[] newChars = new char[chars.length+2*count];
        while (oldIndex > 0) {
            if (chars[oldIndex] == ' ') {
                count--;
                newChars[newIndex--] = '0';
                newChars[newIndex--] = '2';
                newChars[newIndex--] = '%';
            }else {
                newChars[newIndex--] = chars[oldIndex];
            }
            oldIndex--;
        }
        return new String(newChars);
    }

    public static void main(String[] args) {
        new ReplaceSpace().replaceSpace("we are happy");
    }
}
