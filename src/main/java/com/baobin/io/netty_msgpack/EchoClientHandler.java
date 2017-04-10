package com.baobin.io.netty_msgpack;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by hubaobin on 17/4/9.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    private int sendNums = 1000;

    public EchoClientHandler( int sendNums) {
        this.sendNums = sendNums;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] infos = getUserInfo(sendNums);
        ctx.writeAndFlush(infos);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //不需要将byteBuf转换为字符串

        System.out.println("the object i send :" + msg.toString());
        // ctx.writeAndFlush(msg);
    }

    UserInfo[] getUserInfo(int num) {
        UserInfo[] userInfos = new UserInfo[num];
        for (int i = 0; i < num; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername("name" + i);
            userInfo.setId(i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }
}
