package com.baobin.io.bio;

import java.io.*;
import java.net.Socket;

/**
 * Created by hubaobin on 2017/4/7.
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",8081);
            final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        writer.println("what`s the time?");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String result = null;
                        try {
                            result = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(result);
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
