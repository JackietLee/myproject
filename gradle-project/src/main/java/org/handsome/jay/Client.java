package org.handsome.jay;

import org.handsome.jay.framework.JayRpcFramework;
import org.handsome.jay.service.JayService;

public class Client {
    public static void main(String[] args) {
        try {
            JayService localhost = JayRpcFramework.refer(JayService.class, "localhost", 2333);
            String name = localhost.hello("jian");
            System.out.println(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
