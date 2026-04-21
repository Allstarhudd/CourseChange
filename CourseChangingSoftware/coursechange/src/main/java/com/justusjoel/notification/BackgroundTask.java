package com.justusjoel.notification;

public class BackgroundTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Background task running...");
    }
}
