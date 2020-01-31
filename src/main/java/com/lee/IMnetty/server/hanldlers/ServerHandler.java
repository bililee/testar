package com.lee.IMnetty.server.hanldlers;

import com.alibaba.fastjson.JSONObject;
import com.lee.IMnetty.packet.*;
import com.lee.IMnetty.utils.LoginUtil;
import com.lee.IMnetty.utils.SessionUtil;
import com.lee.IMnetty.utils.TinySession;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.juli.logging.Log;

import java.util.UUID;

/**
 * ServerHandler
 *
 * @author Lee
 * @date 2020/1/26
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        PacketCodeC packetCodeC = new PacketCodeC();
        Packet packet = packetCodeC.decode(byteBuf);
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

            // 如果是登陆请求的协议的话，那么直接进行
            if (validLoginInfo(loginRequestPacket)) {

                if (SessionUtil.hasLogin(ctx.channel())) {
                    System.out.println("已经登陆好了");
                }
                // 校验成功
                loginResponsePacket.setSuccess(true);
                loginResponsePacket.setReason("let it go");
                String userId = randomUserId();
                loginResponsePacket.setUserId(userId);
                loginRequestPacket.setUserName(loginRequestPacket.getUserName());
                LoginUtil.markAsLogin(ctx.channel());
                System.out.println(loginRequestPacket.getUserName() + "登陆成功了");
                SessionUtil.bindSession(new TinySession(userId, loginRequestPacket.getUserName()), ctx.channel());
                System.out.println("绑定好了");
            } else {
                // do other thing
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("身份失败");
                // 返回校验失败
            }
//            LoginUtil.markAsLogin(ctx.channel());
            ByteBuf returnMsg = packetCodeC.encode(loginResponsePacket);
            ctx.writeAndFlush(returnMsg);
            //
        }
        if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println("这里拉");
            String toUserid = messageRequestPacket.getToUserid();
            String userName = messageRequestPacket.getUserName();
            StringBuilder sb = new StringBuilder();
            sb.append("toUserid").append(toUserid).append(",userName:").append(userName);
            System.out.println(sb.toString());
            Channel channel = SessionUtil.getChannel(toUserid);
            MessageResponsePacket msgResponsePacket = new MessageResponsePacket();
            MessageResponsePacket msgResponsePacket1 = new MessageResponsePacket();
            System.out.println("进行一次");
            if (channel != null)  {
                String a = userName + ",有人发消息给你:" + messageRequestPacket.getMessgage();
                msgResponsePacket.setMessage(a);
                channel.writeAndFlush(packetCodeC.encode(msgResponsePacket));
                msgResponsePacket1.setMessage("消息发送成功了");
                System.out.println("消息发送成功了");
                ctx.channel().writeAndFlush(packetCodeC.encode(msgResponsePacket1));
            } else {
                System.out.println("没有这个人");
                msgResponsePacket1.setMessage("没有这个人");
                ctx.channel().writeAndFlush(packetCodeC.encode(msgResponsePacket1));
            }
        }
//        super.channelRead(ctx, msg);
    }

    /**
     * 用于进行登陆校验
     * 这里就不进行详细的逻辑操作，直接是可以就行了
     * @param loginRequestPacket
     * @return
     */
    private Boolean validLoginInfo(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
