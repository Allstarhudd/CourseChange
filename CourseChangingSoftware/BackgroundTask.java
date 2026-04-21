package com.coursechange.thread;

public class BackgroundTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Background task running...");
    }
}
