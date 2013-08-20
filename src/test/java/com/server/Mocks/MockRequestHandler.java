package com.server.Mocks;

public class MockRequestHandler implements Runnable {
    public int countRequests = 0;
    @Override
    public void run() {
        countRequests++;
    }
}
