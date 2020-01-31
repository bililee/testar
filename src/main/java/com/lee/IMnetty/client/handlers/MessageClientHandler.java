package com.lee.IMnetty.client.handlers;

import com.lee.IMnetty.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * MessageClientHandler
 *
 * @author Lee
 * @date 2020/1/31
 */
public class MessageClientHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        System.out.println("收到消息" + msg.getMessage());
    }
}
