package com.lee.IMnetty.packet;


import lombok.Data;

import static com.lee.IMnetty.packet.Command.*;
/**
 * MessageResponsePacket
 *
 * @author Lee
 * @date 2020/1/28
 */
@Data
public class MessageResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }

    private String message;

    private String userId;

    private String passwd;

    private String userName;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
