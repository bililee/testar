package com.lee.quartz;

/**
 * Application
 *
 * @author Lee
 * @date 2020/1/12
 */

public class Application {

    /**
     * 普通的尝试的入口
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("start test");
        new Thread(() -> {
            System.out.println("thread start!");
        }).start();
    }
}
