package com.lee.IMnetty.client.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * FirstTestHandler
 *
 * @author Lee
 * @date 2020/1/26
 */
public class FirstTestHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ByteBuf byteBuf = ctx.alloc().buffer();
        byte[] stringbytes = "client connect".getBytes(Charset.forName("UTF-8"));
        byteBuf.writeBytes(stringbytes);
        /**
         * 将信息反馈下
         */
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf msgBuf = (ByteBuf) msg;

        System.out.println("客户端收到消息：" + msgBuf.toString(Charset.forName("UTF-8")));
        super.channelRead(ctx, msg);
    }
}
