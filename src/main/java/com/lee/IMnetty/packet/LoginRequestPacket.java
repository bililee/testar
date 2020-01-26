package com.lee.IMnetty.packet;

/**
 * LoginRequestPacket
 *
 * @author Lee
 * @date 2020/1/26
 */

public class LoginRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return  1;
    }

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户的密码
     */
    private String passwd;

}
