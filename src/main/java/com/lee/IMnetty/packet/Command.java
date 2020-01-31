package com.lee.IMnetty.packet;

/**
 * Command
 * 协议命令
 * @author Lee
 * @date 2020/1/26
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;

    Byte CREATE_GROUP = 5;

    Byte CREATE_GROUP_RESPONSE = 6;
}
