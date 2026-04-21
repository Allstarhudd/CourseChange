package com.justusjoel.notification;

import java.io.IOException;
import java.net.Socket;

public class NotificationClient {

    public void connect() {
        try (Socket socket = new Socket("localhost", 5000)) {
            System.out.println("Connected to server: " + socket.isConnected());
        } catch (IOException e) {
            System.err.println("Failed to connect to notification server: " + e.getMessage());
        }
    }
}
