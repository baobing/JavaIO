package com.baobin.io.netty_msgpack;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * Created by hubaobin on 17/4/9.
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        System.out.println("encoder");
        MessagePack messagePack = new MessagePack();
        System.out.println(JSON.toJSON(o));
        byte[] array = messagePack.write(o);
        System.out.println(JSON.toJSON(array));
        byteBuf.writeBytes(array);

    }
}
