package com.baobin.io.netty_msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * Created by hubaobin on 17/4/9.
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
        byte[] array = new byte[byteBuf.readableBytes()];
        byteBuf.getBytes(byteBuf.readerIndex(), array, 0 , byteBuf.readableBytes());
        MessagePack messagePack = new MessagePack();
        list.add(messagePack.read(array));
    }
}
