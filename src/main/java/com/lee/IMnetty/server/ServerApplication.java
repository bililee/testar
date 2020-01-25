package com.lee.IMnetty.server;

import com.lee.IMnetty.server.servers.NormalServer;

/**
 * ServerApplication
 *
 * @author Lee
 * @date 2020/1/26
 */
public class ServerApplication {

    public static void main(String[] args) {
        System.out.println("server start!");
        NormalServer.run(9896);
    }
}
