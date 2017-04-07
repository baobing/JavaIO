package com.baobin.io.nio;

/**
 * Created by hubaobin on 2017/4/7.
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8081;
        MultiplexerTimeServer server = new MultiplexerTimeServer(port);
        new Thread(server).start();
    }
}
