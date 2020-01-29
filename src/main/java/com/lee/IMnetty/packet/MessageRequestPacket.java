package com.lee.IMnetty.packet;


import lombok.Data;

import static com.lee.IMnetty.packet.Command.MESSAGE_REQUEST;
/**
 * MessageRequestPacket
 * 进行消息的清楚
 * @author Lee
 * @date 2020/1/28
 */
@Data
public class MessageRequestPacket extends Packet{

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    private String messgage;


}
