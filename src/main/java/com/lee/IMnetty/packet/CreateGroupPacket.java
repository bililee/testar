package com.lee.IMnetty.packet;

import java.util.List;

import static com.lee.IMnetty.packet.Command.*;
/**
 * CreateGroupPacket
 *
 * @author Lee
 * @date 2020/1/30
 */
public class CreateGroupPacket extends Packet{

    @Override
    public Byte getCommand() {
        return CREATE_GROUP;
    }

    private boolean success;

    private String groupId;

    private List<String> userList;

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
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
