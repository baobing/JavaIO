package com.baobin.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hubaobin on 2017/4/7.
 */
public class Server {
    public static void main(String[] args) {
        new Server().bio();
    }

    public void bio(){

        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String req = reader.readLine();
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                System.out.println("client ip:" + socket.getInetAddress().getHostAddress());
                System.out.println("client port:" + socket.getPort());
                System.out.println("there is new a request." );
                System.out.println("");
                writer.println("request is " + req);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
