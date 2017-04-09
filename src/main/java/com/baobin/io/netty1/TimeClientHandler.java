package com.baobin.io.netty1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by hubaobin on 17/4/8.
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    private final ByteBuf firstMessae;
    public TimeClientHandler() {
        byte[] req = "query time order".getBytes();
        firstMessae = Unpooled.buffer(req.length);
        firstMessae.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessae);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("now is " + body);
    }
}
