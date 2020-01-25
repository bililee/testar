package com.lee.IMnetty.client.clients;

import com.lee.IMnetty.client.handlers.FirstTestHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

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
                        socketChannel.pipeline().addLast(new FirstTestHandler());
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
}
