package com.baobin.io.netty_msgpack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by hubaobin on 17/4/9.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    public EchoClientHandler() {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("name1");
        userInfo.setId(1);
        ctx.writeAndFlush(userInfo);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //不需要将byteBuf转换为字符串
        System.out.println("the object i send :" + msg.toString() +" ." );
    }
}
