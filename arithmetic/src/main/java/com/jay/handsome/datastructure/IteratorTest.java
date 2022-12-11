package com.jay.handsome.datastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 注释
 *
 * @author jay
 * @date 2022/10/14 14:59
 */
public class IteratorTest {
    public static void main(String[] args) {
        List<String> aggregate = new ArrayList();
        aggregate.add("java");
        aggregate.add("c++");
        aggregate.add("php");
        aggregate.add("敖丙");
        Iterator<String> iterator = aggregate.iterator();
        while (iterator.hasNext()) {
//            iterator.remove(); // 添加这行代码 java.lang.IllegalStateException
            System.out.println(iterator.next());
            iterator.remove(); // 正常
        }
    }

}
