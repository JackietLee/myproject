package com.jay.handsome.jdkproxy;

/**
 * 注释
 *
 * @author jay
 * @date 2022/10/8 14:17
 */
public class UserServiceImpl implements UserService {

    public String name = "111";

    @Override
    public void add() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void select() {
        System.out.println(name);
    }

    @Override
    public void set(String name) {
        this.name = name;
    }

    @Override
    public void a() {
        System.out.println("a");
        b();
    }

    @Override
    public void b() {
        System.out.println("b");
    }
}
