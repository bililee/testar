package com.lee.IMnetty.server;

import com.lee.IMnetty.packet.LoginRequestPacket;
import com.lee.IMnetty.packet.LoginResponsePacket;
import com.lee.IMnetty.packet.PacketCodeC;
import com.lee.IMnetty.server.servers.NormalServer;
import com.lee.IMnetty.utils.LoginUtil;
import io.netty.buffer.ByteBuf;

import java.util.UUID;

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
