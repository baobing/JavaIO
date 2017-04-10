package com.baobin.io.netty_decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by hubaobin on 17/4/8.
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    int counter = 0;
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);*/
        String reqStr = msg.toString();
        System.out.println("the time server receive order : " + reqStr);

        ByteBuf resp = Unpooled.copiedBuffer(((counter++) + " " + System.currentTimeMillis() +
                "" + System.getProperty("line.separator")).getBytes());
        ctx.write(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
