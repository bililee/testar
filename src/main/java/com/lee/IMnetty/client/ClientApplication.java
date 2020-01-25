package com.lee.IMnetty.client;

import com.lee.IMnetty.client.clients.NormalClient;

/**
 * ClientApplication
 *
 * @author Lee
 * @date 2020/1/26
 */
public class ClientApplication {

    public static void main(String[] args) {
        System.out.println("客户端启动了！");
        NormalClient.run(8986);
    }
}
