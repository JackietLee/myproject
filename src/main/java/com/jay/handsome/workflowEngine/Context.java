package com.jay.handsome.workflowEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * 注释
 *
 * @author jay
 * @date 2022/10/12 13:55
 */
public class Context {

    private Map<String, Object> resultMap = new HashMap<>();

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public static void main(String[] args) throws Exception{
        FlowNode flowNode = new FlowNode();
        flowNode.add("one", NodeOne.class, new FlowNode.NodeConf());
        flowNode.add("two", NodeTwo.class, new FlowNode.NodeConf());
        flowNode.add("three",NodeOne.class, new FlowNode.NodeConf());
        flowNode.add("three",NodeTwo.class, new FlowNode.NodeConf());
        FlowEngine flowEngine = new FlowEngine();
        FlowEngine.RunData runData = new FlowEngine.RunData();
        runData.setParamOne("one");
        runData.setParamTwo("two");
        Context context = new Context();
        flowEngine.execute(flowNode, runData, context);
        Map<String, Object> resultMap = context.getResultMap();
        System.out.println(resultMap.get("NodeOne"));
        System.out.println(resultMap.get("NodeTwo"));
    }
}
