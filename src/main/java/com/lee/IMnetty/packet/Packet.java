package com.lee.IMnetty.packet;

import lombok.Data;

/**
 * Packet
 * 协议
 * @author Lee
 * @date 2020/1/26
 */
@Data
public abstract class Packet {

    /**
     * 版本
     */
    private Byte version = 1;

    public Byte getVersion() {
        return version;
    }

    /**
     * 获取相应的操作指令
     * @return
     */
    public abstract Byte getCommand();

}
