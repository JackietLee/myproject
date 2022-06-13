package com.jay.handsome.offer;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 注释
 *
 * @author jay
 * @date 2022/5/6 17:50
 */
public class RecentCounter {

    private Deque<Integer> deque;

    public RecentCounter() {
        deque = new LinkedList<>();
    }

    public int ping(int t) {
        while (!deque.isEmpty() && deque.peekFirst()+3000 <t) {
            deque.pollFirst();
        }
        deque.addLast(t);
        return deque.size();
    }

}
