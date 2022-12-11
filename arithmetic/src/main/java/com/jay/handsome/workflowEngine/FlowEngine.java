package com.jay.handsome.workflowEngine;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 注释
 *
 * @author jay
 * @date 2022/10/12 14:08
 */
public class FlowEngine {
    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.MINUTES,
            new LinkedBlockingQueue<Runnable>(500),
            new ThreadPoolExecutor.AbortPolicy());
    public void execute(FlowNode flowNode, RunData runData, Context context) throws Exception {
        Map<String, List<String>> nodeGroup = groupByGroupName(flowNode);
        Map<String, FlowNode.NodeConf> nodeMap = flowNode.getNodeMap();
        for (String groupName : nodeGroup.keySet()) {
            boolean needThrowExp = false;
            List<String> nodeNameList = nodeGroup.get(groupName);
            if (nodeNameList.size() == 1) {
                String nodeName = nodeNameList.get(0);
                FlowNodeInterface detailNode = (FlowNodeInterface) BeanService.getSingleBeanByType(Class.forName(nodeName));
                NodeExecuteTask nodeParallelTask = new NodeExecuteTask(detailNode, runData, context);
                try {
                    Object execute = nodeParallelTask.execute();
                    context.getResultMap().put(detailNode.resultKey(), execute);
                } catch (Exception e) {
                    needThrowExp = true;
                }
            } else {
                List<Future> resultList = new ArrayList<>();
                List<String> executedNodeNameList = new ArrayList<>();
                List<NodeExecuteTask> executeTaskList = new ArrayList<>();
                for (String nodeName : nodeNameList) {
                    FlowNodeInterface detailNode = (FlowNodeInterface) BeanService.getSingleBeanByType(Class.forName(nodeName));
                    NodeExecuteTask nodeParallelTask = new NodeExecuteTask(detailNode, runData, context);
                    executeTaskList.add(nodeParallelTask);
                    executedNodeNameList.add(nodeName);
                    resultList.add(threadPool.submit(nodeParallelTask));
                }
                for (int i = 0; i < resultList.size(); i++) {
                    String nodeName = executedNodeNameList.get(i);
                    String nodeKey = groupName + "_" + nodeName;
                    FlowNodeInterface detailNode = (FlowNodeInterface) BeanService.getSingleBeanByType(Class.forName(nodeName));
                    FlowNode.NodeConf nodeConf = nodeMap.get(nodeKey);
                    int timeout = nodeConf.getTimeout();
                    Future future = resultList.get(i);
                    try {
                        Object o = future.get(timeout, TimeUnit.MINUTES);
                        context.getResultMap().put(detailNode.resultKey(), o);
                    } catch (InterruptedException e) {
                        needThrowExp = true;
                    } catch (ExecutionException e) {
                        needThrowExp = true;
                    } catch (TimeoutException e) {
                        needThrowExp = true;
                    }
                }
            }
            if (needThrowExp) {
                throw new RuntimeException();
            }
        }
    }

    public static class RunData {
        private String paramOne;
        private String paramTwo;

        public String getParamOne() {
            return paramOne;
        }

        public void setParamOne(String paramOne) {
            this.paramOne = paramOne;
        }

        public String getParamTwo() {
            return paramTwo;
        }

        public void setParamTwo(String paramTwo) {
            this.paramTwo = paramTwo;
        }
    }

    private Map<String, List<String>> groupByGroupName(FlowNode flowNode) {
        Map<String, List<String>> nodeGroup = new LinkedHashMap<>();
        for (String nodeKey : flowNode.getNodeList()) {
            String groupName = getGroupName(nodeKey);
            String nodeName = getNodeName(nodeKey);
            if (!StringUtils.hasText(groupName)) {
                List<String> nodeNameList = new ArrayList<>();
                nodeNameList.add(nodeName);
                nodeGroup.put(nodeName, nodeNameList);
            }else {
                List<String> nodeNameList = nodeGroup.get(groupName);
                if (nodeNameList == null) {
                    nodeNameList = new ArrayList<>();
                }
                nodeNameList.add(nodeName);
                nodeGroup.put(groupName, nodeNameList);

            }
        }
        return nodeGroup;
    }

    private String getGroupName(String nodeKey) {
        String[] arr = nodeKey.split("_");
        return arr.length == 2 ? arr[0] : null;
    }

    private String getNodeName(String nodeKey) {
        String[] arr = nodeKey.split("_");
        return arr.length == 2 ? arr[1] : arr[0];
    }
}
