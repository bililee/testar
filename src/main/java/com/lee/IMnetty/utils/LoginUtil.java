package com.lee.IMnetty.utils;


import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * LoginUtil
 *
 * @author Lee
 * @date 2020/1/28
 */
public class LoginUtil {

    /**
     * 标记下用户已经登陆
     * @param channel
     */
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    /**
     * 确认下用户是否已经登陆
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
