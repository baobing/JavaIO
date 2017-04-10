package com.baobin.io.netty_msgpack;

import org.msgpack.annotation.Message;

import java.io.Serializable;

/**
 * Created by hubaobin on 17/4/9.
 */
 @Message
public class UserInfo implements Serializable{

    private static final long serialVersionUID = 1L;
    private int id;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
