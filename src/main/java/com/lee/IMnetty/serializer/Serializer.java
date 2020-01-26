package com.lee.IMnetty.serializer;

/**
 * Serializer
 * 协议解析器
 * @author Lee
 * @date 2020/1/26
 */
public interface Serializer {

    /**
     * 序列化的算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化对象
     * @param obj
     * @return
     */
    byte[] serialize(Object obj);

    /**
     * 将序列化后的进行反序列化
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T>T deserialize(Class<T> clazz, byte[] bytes);


    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();
}
