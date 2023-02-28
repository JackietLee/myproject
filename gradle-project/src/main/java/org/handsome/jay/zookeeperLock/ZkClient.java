package org.handsome.jay.zookeeperLock;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

public class ZkClient {

    ZooKeeper zk;

    public ZkClient(){
        String serverString = "localhost:2181";
        try {
            zk = new ZooKeeper(serverString, 2000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("-------------------------------");
                    List<String> children = null;
                    try {
                        children = zk.getChildren("/", true);

                        for (String child : children) {
                            System.out.println(child);
                        }
                        System.out.println("-------------------------------");
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ZooKeeper getZk() {

        return zk;
    }
    public void create() throws InterruptedException, KeeperException {
        zk.create("/z", "z".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren("/", true);
        // 延时
        Thread.sleep(Long.MAX_VALUE);
    }
}
