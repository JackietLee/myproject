package com.jay.handsome.workflowEngine;

/**
 * 注释
 *
 * @author jay
 * @date 2022/10/12 14:21
 */
public interface FlowNodeInterface<T> {

    T invokeNode(FlowEngine.RunData nodeData, Context context);

    void afterInvoke(FlowEngine.RunData nodeData, Context context);

    String resultKey();
}
