package com.lee.IMnetty.server.hanldlers;

import com.lee.IMnetty.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * MessageHandler
 *
 * @author Lee
 * @date 2020/1/29
 */
public class MessageHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        String fromUserid = msg.getUserId();
        String userName = msg.getUserName();
        StringBuilder sb = new StringBuilder();
        sb.append("formuserid").append(fromUserid).append(",userName:").append(userName);
        System.out.println(sb.toString());
    }
}
