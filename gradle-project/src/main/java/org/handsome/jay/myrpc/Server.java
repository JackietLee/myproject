package org.handsome.jay.myrpc;

import org.handsome.jay.framework.JayRpcFramework;
import org.handsome.jay.service.JayServiceImpl;

public class Server {
    public static void main(String[] args) {
        try {
            JayServiceImpl jayService = new JayServiceImpl();
            JayRpcFramework.export(jayService, 2333);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}