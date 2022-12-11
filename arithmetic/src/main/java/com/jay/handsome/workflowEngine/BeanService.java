package com.jay.handsome.workflowEngine;

/**
 * 注释
 *
 * @author jay
 * @date 2022/10/12 15:24
 */
public class BeanService {

    private static FlowNodeInterface nodeOne = new NodeOne();
    private static FlowNodeInterface nodeTwo = new NodeTwo();
    public static <T> T getSingleBeanByType(Class<T> forName) {
        if (forName.getName().equals(NodeOne.class.getName())) {
            return (T)nodeOne;
        } else if (forName.getName().equals(NodeTwo.class.getName())) {
            return (T)nodeTwo;
        }
        throw new RuntimeException();
    }
}
