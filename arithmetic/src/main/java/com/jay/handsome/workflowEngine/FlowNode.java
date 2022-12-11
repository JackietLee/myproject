package com.jay.handsome.workflowEngine;

import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 注释
 *
 * @author jay
 * @date 2022/10/12 13:57
 */
public class FlowNode {

    private Map<String, NodeConf> nodeMap = new LinkedHashMap<>();

    public void add(String groupName, Class nodeName, NodeConf nodeConf) {
        String key = null;
        if (StringUtils.hasText(groupName)) {
            key = groupName + "_" + nodeName.getName();
        } else {
            key = nodeName.getName();
        }

        if (nodeMap.containsKey(key)) {
            return;
        }
        nodeMap.put(key, nodeConf);
    }

    public void add(Class nodeName, NodeConf nodeConf) {
        add(nodeName.getName(), nodeName, nodeConf);
    }

    public void replace(String groupName, Class nodeName, NodeConf nodeConf) {
        String key = null;
        if (StringUtils.hasText(groupName)) {
            key = groupName + "_" + nodeName.getName();
        } else {
            key = nodeName.getName();
        }
        nodeMap.put(key, nodeConf);
    }

    public void replace(Class nodeName, NodeConf nodeConf) {
        replace(nodeName.getName(), nodeName, nodeConf);
    }

    public void remove(Class nodeName) {
        remove(nodeName.getName(), nodeName);
    }

    public void remove(String groupName, Class nodeName) {
        String key = null;
        if (StringUtils.hasText(groupName)) {
            key = groupName + "_" + nodeName.getName();
        } else {
            key = nodeName.getName();
        }
        nodeMap.remove(key);
    }

    public Set<String> getNodeList() {
        return nodeMap.keySet();
    }

    public Map<String, NodeConf> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(Map<String, NodeConf> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public static class NodeConf {
        private int timeout = 100;
        public NodeConf() {
        }

        public NodeConf(int timeout) {
            this.timeout = timeout;
        }

        public int getTimeout() {
            return timeout;
        }

        public void setTimeout(int timeout) {
            this.timeout = timeout;
        }
    }
}
