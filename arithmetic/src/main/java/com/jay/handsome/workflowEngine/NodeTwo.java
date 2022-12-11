package com.jay.handsome.workflowEngine;

/**
 * 注释
 *
 * @author jay
 * @date 2022/10/12 14:55
 */
public class NodeTwo implements FlowNodeInterface{
    @Override
    public Object invokeNode(FlowEngine.RunData nodeData, Context context) {
        System.out.println("执行方法"+ nodeData.getParamTwo());
        return nodeData.getParamTwo();
    }

    @Override
    public void afterInvoke(FlowEngine.RunData nodeData, Context context) {

    }

    @Override
    public String resultKey() {
        return "NodeTwo";
    }
}
