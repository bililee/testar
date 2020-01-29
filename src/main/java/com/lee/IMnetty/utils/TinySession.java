package com.lee.IMnetty.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * TinySession
 * 只用来存储一些简单的session的信息
 * @author Lee
 * @date 2020/1/29
 */

@Data
@Getter
@Setter
public class TinySession {

    /**
     * 用户的id
     */
    private String userId;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
