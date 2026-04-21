package com.justusjoel.notification;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NotificationServer {

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(5000);
             Socket socket = serverSocket.accept()) {

            System.out.println("Client connected: " + socket.isConnected());

        } catch (IOException e) {
            System.err.println("Notification server error: " + e.getMessage());
        }
    }
}
