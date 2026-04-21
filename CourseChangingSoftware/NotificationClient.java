package com.coursechange.network;

import java.net.Socket;

public class NotificationClient {

    public void connect() throws Exception {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Connected to server");
    }
}
