package com.baobin.io.netty_msgpack;

import org.msgpack.MessagePack;

/**
 * Created by hubaobin on 17/4/10.
 */
public class MessagePackTest {



    public static void main(String[] args) throws Exception{
        MessagePack pack = new MessagePack();
        UserInfo u = new UserInfo();
        u.setId(1);
        u.setUsername("name1");
        //序列化
        byte[] bytes = pack.write(u);

        //反序列化
        UserInfo s = pack.read(bytes, UserInfo.class);
        System.out.println("DeviceID: "+s.getUsername());
    }

}
