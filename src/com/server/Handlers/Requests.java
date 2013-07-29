package com.server.Handlers;

public interface Requests {
    String getMethod();
    String getPath();
    String getHttpVersion();
    String getParam(String query);
    String getBody();
    String getHeader(String accept);
}
