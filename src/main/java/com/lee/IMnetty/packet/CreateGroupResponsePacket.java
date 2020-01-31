package com.lee.IMnetty.packet;

import java.util.ArrayList;
import java.util.List;

import static com.lee.IMnetty.packet.Command.*;

/**
 * CreateGroupResponsePacket
 *
 * @author Lee
 * @date 2020/1/30
 */
public class CreateGroupResponsePacket extends Packet{

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }

    private boolean success;

    private String groupId;

    private List<String> userName;

    public List<String> getUserName() {
        return userName;
    }

    public void setUserName(List<String> userName) {
        this.userName = userName;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
