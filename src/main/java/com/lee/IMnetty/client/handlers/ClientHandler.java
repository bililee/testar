package com.lee.IMnetty.client.handlers;

import com.lee.IMnetty.packet.*;
import com.lee.IMnetty.utils.SessionUtil;
import com.lee.IMnetty.utils.TinySession;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

/**
 * ClientHandler
 *
 * @author Lee
 * @date 2020/1/26
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("客户端开始登陆");
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUserId(UUID.randomUUID().toString());
//        loginRequestPacket.setPassword("123");
//        loginRequestPacket.setUserName("test1");
//        PacketCodeC packetCodeC = new PacketCodeC();
//        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
//        ctx.writeAndFlush(byteBuf);
//        super.channelActive(ctx);
    }


    /**
     * 客户端接收到响应之后的操作
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PacketCodeC packetCodeC = new PacketCodeC();
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(byteBuf.toString());
        Packet responsepacket = packetCodeC.decode(byteBuf);
        if (responsepacket instanceof LoginResponsePacket) {
            LoginResponsePacket packet = (LoginResponsePacket) responsepacket;
            if (packet.getSuccess()) {
                SessionUtil.bindSession(new TinySession(packet.getUserId(), packet.getUserName()), ctx.channel());
                System.out.println("登陆成功啊,userid:"+packet.getUserId());
            } else {
                System.out.println("登陆失败了，原因是：" + packet.getReason());
            }
        }
        if (responsepacket instanceof MessageResponsePacket) {
            responsepacket = (MessageResponsePacket) responsepacket;
            System.out.println(((MessageResponsePacket) responsepacket).getMessage());
        }
//        System.out.println("something error!");
//        super.channelRead(ctx, msg);
    }
}
