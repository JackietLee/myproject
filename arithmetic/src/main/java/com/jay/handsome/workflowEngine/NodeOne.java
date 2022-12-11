package com.jay.handsome.workflowEngine;

/**
 * 注释
 *
 * @author jay
 * @date 2022/10/12 14:54
 */
public class NodeOne implements FlowNodeInterface{
    @Override
    public Object invokeNode(FlowEngine.RunData nodeData, Context context) {
        System.out.println("执行方法"+ nodeData.getParamOne());
        return nodeData.getParamOne();
    }

    @Override
    public void afterInvoke(FlowEngine.RunData nodeData, Context context) {

    }

    @Override
    public String resultKey() {
        return "NodeOne";
    }
}
