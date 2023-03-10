package com.jay.handsome.datastructure;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU  Least Recently Used 最近最少使用
 *
 * @author jay
 * @date 2022/9/1 11:48
 */
public class LRU<K,V> extends LinkedHashMap<K,V> {
    private final int CACHE_SIZE;

    public LRU(int initialCapacity) {
        super((int) (Math.ceil(initialCapacity/0.75) + 1), 0.75f, true);
        CACHE_SIZE = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > CACHE_SIZE;
    }
}
