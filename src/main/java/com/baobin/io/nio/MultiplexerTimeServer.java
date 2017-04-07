package com.baobin.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hubaobin on 2017/4/7.
 */
public class MultiplexerTimeServer implements Runnable {
    private ServerSocketChannel serverChannel;
    private Selector selector;
    private boolean stop = false;

    public MultiplexerTimeServer(int port) {

        try {
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("server is start in port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务初始化失败");
        }
    }

    public void stop() {
        stop = true;
    }

    @Override
    public void run() {
        while (!stop) {

            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();

                    if (!selectionKey.isValid()) {
                        continue;
                    }

                    //新的客户端
                    if (selectionKey.isAcceptable()) {
                        try {
                            System.out.println("新客户端建立");
                            handlerAccept(selectionKey);
                        } catch (Exception e) {
                            selectionKey.cancel();
                            if (null != selectionKey.channel()) {
                                selectionKey.channel().close();
                            }
                            System.out.println("新客户端异常");
                        }
                    }

                    //客户端消息
                    if (selectionKey.isReadable()) {
                        try {
                            handlerRead(selectionKey);
                        } catch (Exception e) {
                            selectionKey.cancel();
                            if (null != selectionKey.channel()) {
                                selectionKey.channel().close();
                            }
                            System.out.println("读取客户端信息异常");
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("监听事件异常");
            }
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handlerAccept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        if (null == serverChannel) {
            System.out.println();
        }
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

    }

    private void handlerRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int size = clientChannel.read(readBuffer);

        if (size > 0) {
            readBuffer.flip();
            byte[] bytes = new byte[readBuffer.remaining()];
            readBuffer.get(bytes);
            System.out.println("client send msg:\n" + new String(bytes, "UTF-8"));

            String rep = "" + System.currentTimeMillis();
            doWrite(clientChannel, rep);
        } else if (size < 0) {
            System.out.println("read size < 0");
            key.cancel();
            clientChannel.close();
        }

    }

    private void doWrite(SocketChannel channel, String rep) throws IOException {

        if (rep == null || rep.trim().isEmpty()) {
            return;
        }

        byte[] repBytes = rep.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(repBytes.length);
        writeBuffer.clear();
        writeBuffer.put(repBytes);
        writeBuffer.flip();
        channel.write(writeBuffer);

    }
}
