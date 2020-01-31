package com.lee.IMnetty.utils;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SessionUtil
 * 用来存储连接进来的用户端额相关信息
 * @author Lee
 * @date 2020/1/29
 */
public class SessionUtil {

    public static final Map<String , Channel> userIdChannelMap = new ConcurrentHashMap<>();

    /**
     * 绑定相应的session
     * @param session
     * @param channel
     */
    public static void bindSession(TinySession session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
        System.out.println("成功看下" + channel.hasAttr(Attributes.SESSION));
    }

    /**
     * 删除相应的session
     * @param session
     */
    public static void unBindSession(TinySession session) {
        userIdChannelMap.remove(session.getUserId());
    }

    /**
     * 判断用户是否登陆
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel) {
        System.out.println("看下" + channel.hasAttr(Attributes.SESSION));
        return channel.hasAttr(Attributes.SESSION);
    }

    /**
     * 获取到相应的session
     * @param channel
     * @return
     */
    public static TinySession getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    /**
     * 获取相应的channel
     * @param userId
     * @return
     */
    public static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }
}
