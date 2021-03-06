package com.baobin.io.netty_decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by hubaobin on 17/4/8.
 */
public class TimeClientHandler extends ChannelHandlerAdapter {

    private static int counter;
    public TimeClientHandler() {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(counter);
        //一定要加换行符

        for (int i = 0;i < 100;i++) {
            byte[] req = ((counter++) + " " + "query time order" + System.getProperty("line.separator")).getBytes();
            ByteBuf firstMessae = Unpooled.buffer(req.length);
            firstMessae.writeBytes(req);
            ctx.writeAndFlush(firstMessae);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
     /*   ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");*/
        //不需要将byteBuf转换为字符串
        System.out.println("now is " + msg.toString() +" ." );
    }
}
