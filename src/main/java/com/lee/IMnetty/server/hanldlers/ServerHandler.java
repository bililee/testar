package com.lee.IMnetty.server.hanldlers;

import com.alibaba.fastjson.JSONObject;
import com.lee.IMnetty.packet.LoginRequestPacket;
import com.lee.IMnetty.packet.LoginResponsePacket;
import com.lee.IMnetty.packet.Packet;
import com.lee.IMnetty.packet.PacketCodeC;
import com.lee.IMnetty.utils.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.juli.logging.Log;

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
                // 校验成功
                loginResponsePacket.setSuccess(true);
                loginResponsePacket.setReason("let it go");
                LoginUtil.markAsLogin(ctx.channel());
            } else {
                // do other thing
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("身份失败");
                // 返回校验失败
            }
            LoginUtil.markAsLogin(ctx.channel());
            ByteBuf returnMsg = packetCodeC.encode(loginResponsePacket);
            ctx.writeAndFlush(returnMsg);
            //
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
}
