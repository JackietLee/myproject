package com.jay.handsome.workflowEngine;

import java.util.concurrent.Callable;

/**
 * 注释
 *
 * @author jay
 * @date 2022/10/12 14:19
 */
public class NodeExecuteTask implements Callable {

    private FlowNodeInterface flowNodeInterface;
    private FlowEngine.RunData runData;
    private Context context;

    public NodeExecuteTask(FlowNodeInterface flowNodeInterface, FlowEngine.RunData runData, Context context) {
        this.flowNodeInterface = flowNodeInterface;
        this.runData = runData;
        this.context = context;
    }

    @Override
    public Object call() throws Exception {
        return execute();
    }

    public Object execute() {
        Object o = flowNodeInterface.invokeNode(runData, context);
        flowNodeInterface.afterInvoke(runData, context);
        return o;
    }
}
