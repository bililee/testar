package com.lee.testar.service;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * BaseZookeeper
 *
 * @author Lee
 * @date 2019/12/11
 */
public class BaseZookeeper implements Watcher {

    private ZooKeeper zooKeeper;

    private static final int SESSION_TIME_OUT = 2000;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public BaseZookeeper(String host) {
        try {
            this.conncetZookeeper(host);
        } catch (Exception exp) {
            System.out.println("something error when connecting to zookeeper");
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            System.out.println("watcher received event");
            countDownLatch.countDown();
        }
    }


    public void conncetZookeeper(String host) throws Exception {
        zooKeeper = new ZooKeeper(host, SESSION_TIME_OUT, this);
        countDownLatch.await();
        System.out.println("zookeeper connect success");
    }

    /**
     * 创建一个节点
     * @param path
     * @param data
     * @return
     * @throws Exception
     */
    public String createNode(String path, String data) throws Exception {
        return this.zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public List<String> getChildren(String path) throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren(path, false);
        return children;
    }

    public Stat setData(String path, String data) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.setData(path, data.getBytes(), -1);
        return stat;
    }

    public void deleteNode(String path) throws InterruptedException, KeeperException {
        zooKeeper.delete(path, -1);
    }

    public String getCTime(String path) throws InterruptedException, KeeperException {
        Stat stat = zooKeeper.exists(path, false);
        return String.valueOf(stat.getCtime());
    }

    public void closeConnection() throws InterruptedException {
        if (zooKeeper != null) {
            zooKeeper.close();
        }
    }
}
