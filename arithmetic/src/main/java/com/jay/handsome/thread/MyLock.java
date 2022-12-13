package com.jay.handsome.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 注释
 *
 * @author jay
 * @date 2022/12/12 11:47
 */
public class MyLock {

    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void test() {
        try {
            condition.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void test1() {
        condition.signal();
    }
}
