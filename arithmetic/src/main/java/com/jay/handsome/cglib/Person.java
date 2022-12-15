package com.jay.handsome.cglib;

/**
 * 注释
 *
 * @author jay
 * @date 2022/6/27 16:39
 */
public class Person {

    public String name = "name";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void one() {
        System.out.println(1);
        two();
    }

    public void two() {
        System.out.println(2);
    }
}
