package com.lee.IMnetty.serializer;

import com.alibaba.fastjson.JSON;

/**
 * JSONSerializer
 * 使用fastjson
 * @author Lee
 * @date 2020/1/26
 */
public class JSONSerializer implements Serializer{
    @Override
    public byte getSerializerAlgorithm() {
        return 1;
    }

    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
