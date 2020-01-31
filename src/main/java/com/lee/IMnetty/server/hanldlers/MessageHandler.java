package com.lee.IMnetty.server.hanldlers;

import com.lee.IMnetty.packet.MessageRequestPacket;
import com.lee.IMnetty.packet.MessageResponsePacket;
import com.lee.IMnetty.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * MessageHandler
 *
 * @author Lee
 * @date 2020/1/29
 */
public class MessageHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        System.out.println("come into");
        String toUserid = msg.getToUserid();
        String userName = msg.getUserName();
        StringBuilder sb = new StringBuilder();
        sb.append("toUserid").append(toUserid).append(",userName:").append(userName);
        System.out.println(sb.toString());
        Channel channel = SessionUtil.getChannel(toUserid);
        MessageResponsePacket msgResponsePacket = new MessageResponsePacket();
        MessageResponsePacket msgResponsePacket1 = new MessageResponsePacket();
        if (channel != null)  {
            String a = userName + ",有人发消息给你:" + msg.getMessgage();
            msgResponsePacket.setMessage(a);
            channel.writeAndFlush(msgResponsePacket);
            msgResponsePacket1.setMessage("消息发送成功了");
            ctx.channel().writeAndFlush(msgResponsePacket1);
        } else {
            System.out.println("没有这个人");
            msgResponsePacket1.setMessage("没有这个人");
            ctx.channel().writeAndFlush(msgResponsePacket1);
        }
    }
}
