package com.lee.IMnetty.server.servers;

import com.lee.IMnetty.server.hanldlers.FirstTestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * NormalServer
 *
 * @author Lee
 * @date 2020/1/26
 */
public class NormalServer {

    /**
     * 正常跑服务端的入口
     */
    public static void run(Integer port) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();

        NioEventLoopGroup workerGroup = new NioEventLoopGroup(2);

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel SocketChannel) throws Exception {
                        System.out.println("come into initchannel");
                        SocketChannel.pipeline().addLast(new FirstTestHandler());
                    }
                });
        bootstrap.bind(port);
    }
}
