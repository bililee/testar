package com.lee.IMnetty.packet;

import com.lee.IMnetty.serializer.JSONSerializer;
import com.lee.IMnetty.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * PacketCodeC
 *
 * @author Lee
 * @date 2020/1/26
 */
public class PacketCodeC {
    private static int MAGIC_NUMBER = 0x123;

    /**
     * 协议组装的方式
     * @param packet
     * @return
     */
    public ByteBuf encode(Packet packet) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();

        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        /**
         * 组装协议
         */
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;

    }

    /**
     * 进行协议的解析
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf) {
        // 跳过一个int, 即固定前缀
        byteBuf.skipBytes(4);

        // 跳过一个版本号
        byteBuf.skipBytes(1);

        Byte serializerAlgorithm = byteBuf.readByte();

        Byte command = byteBuf.readByte();

        Integer length = byteBuf.readInt();
        byte[] by = new byte[length];
        byteBuf.readBytes(by);
        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, by);
        }
        return null;
    }

    public Class<? extends Packet> getRequestType(Byte command) {
        byte oneCommand = 1;
        if (command.equals(oneCommand)) {
            return LoginRequestPacket.class;
        }
        return LoginRequestPacket.class;
    }

    public Serializer getSerializer(byte alogrithm) {
        if (alogrithm == 1) {
            return new JSONSerializer();
        }
        return null;
    }
}