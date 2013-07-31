package com.server.Responses;

import java.util.Hashtable;

public class MessageHeader {
    private String contentType;
    private String contentLength = "0";
    private String connection = "close";
    private String server = "Totoro/1.0";

    public MessageHeader(String type) {
        contentType = type;
    }

    public MessageHeader(String type , int size) {
        contentType = type;
        contentLength = String.valueOf(size);
    }

    public String getContentType() {
        return contentType;
    }

    public String getContentLength() {
        return contentLength;
    }

    public String getConnection() {
        return connection;
    }

    public Hashtable<String, String> getMessageHeader() {
        Hashtable<String, String> messageHeader = new Hashtable<String, String>();
        messageHeader.put("Content-Type", contentType);
        messageHeader.put("Content-Length", contentLength);
        messageHeader.put("connection", connection);
        return messageHeader;
    }

    public String getServer() {
        return server;
    }
}
