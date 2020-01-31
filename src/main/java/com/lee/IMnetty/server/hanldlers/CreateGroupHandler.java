package com.lee.IMnetty.server.hanldlers;

import com.lee.IMnetty.packet.CreateGroupPacket;
import com.lee.IMnetty.packet.CreateGroupResponsePacket;
import com.lee.IMnetty.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * CreateGroupHandler
 *
 * @author Lee
 * @date 2020/1/30
 */
public class CreateGroupHandler extends SimpleChannelInboundHandler<CreateGroupPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupPacket createGroupPacket) throws Exception {
        List<String> userList = createGroupPacket.getUserList();
        List<String> userNameList = new ArrayList();
        if (userList.size() <= 0) {
            System.out.println("人数不够");
        }
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        for (String userId : userList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }
        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setUserName(userNameList);
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(UUID.randomUUID().toString());

        channelGroup.writeAndFlush(responsePacket);

        System.out.println("创建群聊成功，群id为" + responsePacket.getGroupId());
        StringBuilder member = new StringBuilder();
        userNameList.forEach(item-> {
            member.append(item + ",");
        });
        System.out.println("群成员为" + member.toString());
    }
}
