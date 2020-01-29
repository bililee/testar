package com.lee.IMnetty.utils;

import io.netty.util.AttributeKey;

/**
 * Attributes
 *
 * @author Lee
 * @date 2020/1/28
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<TinySession> SESSION = AttributeKey.newInstance("session");
}
