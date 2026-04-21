package com.coursechange.network;

import java.net.ServerSocket;
import java.net.Socket;

public class NotificationServer {

    public void startServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");
    }
}
