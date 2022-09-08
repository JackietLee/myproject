package com.hello;

import com.test.Test;

/**
 * 注释
 *
 * @author jay
 * @date 2022/9/8 11:17
 */
public class Client {
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.hello());
    }
}
