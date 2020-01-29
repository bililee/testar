package com.lee.IMnetty.packet;

import lombok.Data;
import static com.lee.IMnetty.packet.Command.LOGIN_REQUEST;
/**
 * LoginRequestPacket
 *
 * @author Lee
 * @date 2020/1/26
 */
@Data
public class LoginRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return  LOGIN_REQUEST;
    }

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户的密码
     */
    private String password;

    /**
     * 用户的名字
     */
    private String userName;




    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
