package com.jay.handsome.mianshi;

import java.util.*;

// 写不出来
public class answer2 {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B.A", 2);
        map.put("B.B", 3);
        map.put("CC.D.E", 4);
        map.put("CC.D.F", 8);
        Map<String, Object> transform = transform(map);
        System.out.println(transform);
    }

    public static HashMap<String, Object> transform(HashMap<String, Integer> map) {
        ArrayList<HashMap> List = new ArrayList<HashMap>();
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            List.add(transform(entry.getKey().split("\\."), 0, entry.getValue()));
        }

        return new HashMap<>();
    }

    public static HashMap<String, Object> transform(String[] split, int index, Integer value) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        if (split.length-1 == index) {
            stringObjectHashMap.put(split[index], value);
            return stringObjectHashMap;
        }
        stringObjectHashMap.put(split[index], transform(split, ++index, value));
        return stringObjectHashMap;
    }
}
