package org.handsome.jay.zookeeperLock;

import org.apache.zookeeper.KeeperException;

public class TestListener {

    public static void main(String[] args) throws InterruptedException, KeeperException {
        ZkClient zkClient = new ZkClient();
        zkClient.getChildren();
    }
}
