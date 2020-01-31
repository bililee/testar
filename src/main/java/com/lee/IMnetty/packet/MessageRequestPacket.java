package com.lee.IMnetty.packet;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static com.lee.IMnetty.packet.Command.MESSAGE_REQUEST;
/**
 * MessageRequestPacket
 * 进行消息的清楚
 * @author Lee
 * @date 2020/1/28
 */
@Data
@Getter
@Setter
public class MessageRequestPacket extends Packet{

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    private String messgage;

    private String toUserid;

    private String userName;

    public String getMessgage() {
        return messgage;
    }

    public void setMessgage(String messgage) {
        this.messgage = messgage;
    }

    public String getToUserid() {
        return toUserid;
    }

    public void setToUserid(String toUserid) {
        this.toUserid = toUserid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
