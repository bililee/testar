package com.lee.IMnetty.client.clients;

import com.lee.IMnetty.client.handlers.ClientHandler;
import com.lee.IMnetty.client.handlers.FirstTestHandler;
import com.lee.IMnetty.packet.LoginRequestPacket;
import com.lee.IMnetty.utils.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

/**
 * NormalClient
 *
 * @author Lee
 * @date 2020/1/26
 */
public class NormalClient {

    public static void run(Integer port) {
        NioEventLoopGroup clientLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(clientLoopGroup)
                .channel(NioSocketChannel.class)
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
//                .option(ChannelOption.SO_KEEPALIVE, true)
//                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        System.out.println("客户端端正式启动");
//                        socketChannel.pipeline().addLast(new FirstTestHandler());
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        bootstrap.connect("127.0.0.1", 9896).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("success!!");
            } else {
                System.out.println("failed because of something unkonwn!");
            }
        });
    }

    public static void startConsoleThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        new Thread(()-> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    String username = scanner.nextLine();
                    loginRequestPacket.setUserName(username);
                    loginRequestPacket.setPassword("pwd");
                    channel.writeAndFlush(loginRequestPacket);
                    // 等待相应
                    waitForResponse();
                }
            }


        });
    }

    public static void waitForResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exp) {

        }
    }
}
