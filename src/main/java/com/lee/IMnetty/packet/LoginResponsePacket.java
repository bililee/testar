package com.lee.IMnetty.packet;

import static com.lee.IMnetty.packet.Command.*;

/**
 * LoginResponsePacket
 * 这里是登陆验证返回
 * @author Lee
 * @date 2020/1/28
 */
public class LoginResponsePacket extends Packet{
    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }

    private Boolean success;

    private String reason;


    private String userId;


    private  String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
