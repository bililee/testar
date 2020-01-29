package com.lee.IMnetty.server.hanldlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * FirstTestHandler
 *
 * @author Lee
 * @date 2020/1/26
 */
public class FirstTestHandler extends ChannelInboundHandlerAdapter {


    /**
     * 连接成功之后写下数据
     *
     * @param channelHandlerContext
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("开始进入到channelactive中来了");
        ByteBuf byteBuf = channelHandlerContext.channel().alloc().buffer();
        byte[] bytes = "连接成功".getBytes(Charset.forName("UTF-8"));
        byteBuf.writeBytes(bytes);
        // 将连接成功的信息返回给客户端
        channelHandlerContext.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.pipeline();
        ByteBuf msgBuf = (ByteBuf) msg;
        System.out.println("服务端接收到消息:" + msgBuf.toString(Charset.forName("UTF-8")));
        super.channelRead(ctx, msg);
    }
}
